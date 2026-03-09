package stratostrike.GuiView.EventHandlers;

import java.util.HashMap;
import java.util.Map;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.Domain.Model.Observer;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.screens.GameScreenLocal;
import stratostrike.GuiView.screens.SelectionScreenLocal;
import stratostrike.GameEvent;

/**
 * Dispatcher centralizzato per la gestione degli eventi di gioco nella GUI locale.
 * Agisce come Observer di entrambi i controller (MakeTurn e SetupArmy) e
 * instrada ogni GameEvent all'handler GUI appropriato.
 *
 * Gestisce internamente la transizione dalla fase di setup alla fase di gioco:
 * quando SetupArmy emette SELECT_SHIP (tutte le armate scelte), avvia il match
 * e naviga alla schermata di gioco.
 */
public class GuiEventDispatcher implements Observer {

    private final Map<GameEvent, GuiEventHandler> setupHandlers;
    private final Map<GameEvent, GuiEventHandler> gameHandlers;

    private final MakeTurn makeTurn;
    private final SetupArmy setupArmy;
    private final LoopingTurn loopingTurn;
    private final SelectionScreenLocal selectionScreen;
    private final GameScreenLocal gameScreen;
    private final NavigationControllerLocal navigator;

    /** true finché siamo nella fase di selezione armate */
    private boolean setupPhase = true;

    public GuiEventDispatcher(
        MakeTurn makeTurn,
        SetupArmy setupArmy,
        LoopingTurn loopingTurn,
        SelectionScreenLocal selectionScreen,
        GameScreenLocal gameScreen,
        NavigationControllerLocal navigator
    ) {
        this.makeTurn = makeTurn;
        this.setupArmy = setupArmy;
        this.loopingTurn = loopingTurn;
        this.selectionScreen = selectionScreen;
        this.gameScreen = gameScreen;
        this.navigator = navigator;

        this.setupHandlers = initializeSetupHandlers();
        this.gameHandlers = initializeGameHandlers();

        // Si registra su entrambi i controller
        setupArmy.addObserver(this);
        makeTurn.addObserver(this);
    }

    @Override
    public void update() {
        GameEvent event = makeTurn.getGame().getCurrentEvent();

        if (setupPhase) {
            // SELECT_SHIP in fase di setup significa che tutti i giocatori
            // hanno scelto l'armata: si avvia il match e si naviga al gioco
            if (event == GameEvent.SELECT_SHIP) {
                setupPhase = false;
                loopingTurn.startUIMatch();
                navigator.navigateToGame();
                return;
            }
            setupHandlers.getOrDefault(event, new DefaultGuiHandler()).handle(
                makeTurn, setupArmy, loopingTurn, selectionScreen, gameScreen, navigator
            );
        } else {
            gameHandlers.getOrDefault(event, new DefaultGuiHandler()).handle(
                makeTurn, setupArmy, loopingTurn, selectionScreen, gameScreen, navigator
            );
        }
    }

    // -----------------------------------------------------------------------

    private Map<GameEvent, GuiEventHandler> initializeSetupHandlers() {
        Map<GameEvent, GuiEventHandler> handlers = new HashMap<>();
        GuiEventHandler refreshSelection = new SelectArmyGuiHandler();
        handlers.put(GameEvent.SELECT_ARMY,   refreshSelection);
        handlers.put(GameEvent.COMPOSE_ARMY,  refreshSelection);
        return handlers;
    }

    private Map<GameEvent, GuiEventHandler> initializeGameHandlers() {
        Map<GameEvent, GuiEventHandler> handlers = new HashMap<>();
        GuiEventHandler defaultHandler = new DefaultGuiHandler();

        handlers.put(GameEvent.PLAYER1_TURN_STARTED,   defaultHandler);
        handlers.put(GameEvent.PLAYER2_TURN_STARTED,   defaultHandler);
        handlers.put(GameEvent.SELECT_SHIP,            defaultHandler);
        handlers.put(GameEvent.SELECT_POSITION,        defaultHandler);
        handlers.put(GameEvent.SHIP_HIT,               defaultHandler);
        handlers.put(GameEvent.SHIP_MISSED,            defaultHandler);
        handlers.put(GameEvent.SHIP_DESTROYED,         defaultHandler);

        handlers.put(GameEvent.SELECT_ACTION,          new SelectActionGuiHandler());
        handlers.put(GameEvent.EXECUTE_ACTION,         new ExecuteActionGuiHandler());
        handlers.put(GameEvent.SPECIAL_ACTION_SELECTED, new SpecialActionGuiHandler());
        handlers.put(GameEvent.TURN_ENDED,             new TurnEndedGuiHandler());

        return handlers;
    }
}
