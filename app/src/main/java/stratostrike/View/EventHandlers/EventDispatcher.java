package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.Controller.EventSource;
import stratostrike.Domain.Model.Observer;
import stratostrike.GameEvent;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

import java.util.HashMap;
import java.util.Map;

/**
 * Dispatcher centralizzato per la gestione degli eventi di gioco.
 * Mantiene la mappatura tra GameEvent e i rispettivi EventHandler.
 * Agisce come Observer di entrambi i controller (MakeTurn e SetupArmy).
 */
public class EventDispatcher implements Observer {
    
    private final Map<GameEvent, EventHandler> eventHandlers;
    private final MakeTurn makeTurn;
    private final SetupArmy setupArmy;
    private final SelectionView selectionView;
    private final GameOutputView gameOutputView;
    private final SetupOutputView setupOutputView;
    
    public EventDispatcher(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView, GameOutputView gameOutputView, SetupOutputView setupOutputView) {
        this.makeTurn = makeTurn;
        this.setupArmy = setupArmy;
        this.selectionView = selectionView;
        this.gameOutputView = gameOutputView;
        this.setupOutputView = setupOutputView;
        this.eventHandlers = initializeEventHandlers();
        
        // Registra questo dispatcher come observer di entrambi i controller
        makeTurn.addObserver(this);
        setupArmy.addObserver(this);
    }
    
    /**
     * Metodo chiamato quando lo stato del gioco cambia.
     * Riceve il source (EventSource) che ha notificato il cambio di stato.
     * @param source il controller che ha notificato (implementa EventSource)
     */
    @Override
    public void update(EventSource source) {
        GameEvent currentEvent = source.getCurrentEvent();
        
        // Per eventi di gioco normale, visualizza prima lo stato
        if (!isSetupEvent(currentEvent)) {
            gameOutputView.displayGameState(makeTurn);
        }
        
        // Dispatcha all'handler appropriato
        dispatch(currentEvent);
    }
    
    /**
     * Dispatcha l'evento all'handler appropriato.
     */
    private void dispatch(GameEvent event) {
        EventHandler handler = eventHandlers.getOrDefault(event, new DefaultEventHandler());
        handler.handle(makeTurn, setupArmy, selectionView, gameOutputView, setupOutputView);
    }
    
    /**
     * Verifica se un evento è di tipo setup.
     */
    private boolean isSetupEvent(GameEvent event) {
        return event == GameEvent.SELECT_ARMY || event == GameEvent.COMPOSE_ARMY;
    }
    
    /**
     * Inizializza la mappa che associa ogni GameEvent al suo handler specifico.
     */
    private Map<GameEvent, EventHandler> initializeEventHandlers() {
        Map<GameEvent, EventHandler> handlers = new HashMap<>();
        EventHandler defaultHandler = new DefaultEventHandler();
        
        // Handler per eventi di setup
        handlers.put(GameEvent.SELECT_ARMY, new SelectArmyEventHandler());
        handlers.put(GameEvent.COMPOSE_ARMY, new ComposeArmyEventHandler());
        
        // Handler per eventi di gioco
        handlers.put(GameEvent.SELECT_SHIP, new SelectShipEventHandler());
        handlers.put(GameEvent.SELECT_ACTION, new SelectActionEventHandler());
        handlers.put(GameEvent.SELECT_POSITION, new SelectPositionEventHandler());
        handlers.put(GameEvent.EXECUTE_ACTION, new ExecuteActionEventHandler());
        handlers.put(GameEvent.SPECIAL_ACTION_SELECTED, new SpecialActionSelectedEventHandler());
        
        // Per gli altri eventi, usa l'handler di default
        handlers.put(GameEvent.TURN_STARTED, defaultHandler);
        handlers.put(GameEvent.SHIP_DESTROYED, defaultHandler);
        handlers.put(GameEvent.SHIP_HIT, defaultHandler);
        handlers.put(GameEvent.SHIP_MISSED, defaultHandler);
        handlers.put(GameEvent.TURN_ENDED, defaultHandler);
        handlers.put(GameEvent.GAME_WON, defaultHandler);
        handlers.put(GameEvent.GAME_LOST, defaultHandler);
        handlers.put(GameEvent.PLAYER1_TURN_STARTED, defaultHandler);
        handlers.put(GameEvent.PLAYER2_TURN_STARTED, defaultHandler);
        
        return handlers;
    }
    

}
