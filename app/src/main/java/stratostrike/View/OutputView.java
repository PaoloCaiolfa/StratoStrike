package stratostrike.View;


import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.Domain.Model.Observer;
import stratostrike.GameEvent;
import stratostrike.View.EventHandlers.*;

import java.util.HashMap;
import java.util.Map;


public class OutputView implements Observer {

    MakeTurn makeTurn;
    SetupArmy setupArmy;
    
    BoardView boardView;
    SelectionView selectionView;
    
    // Mappa che associa ogni evento al suo handler specifico
    private final Map<GameEvent, EventHandler> eventHandlers;
  

    public OutputView(MakeTurn makeTurn, SetupArmy setupArmy) {
        this.makeTurn = makeTurn;
        this.setupArmy = setupArmy;
        makeTurn.addObserver(this);
        this.boardView = new BoardView();
        this.selectionView = new SelectionView(makeTurn, setupArmy);
        
        // Inizializza la mappa degli handler
        this.eventHandlers = initializeEventHandlers();
    }
    
    /**
     * Inizializza la mappa che associa ogni GameEvent al suo handler specifico.
     
     * @return la mappa degli event handler
     */
    private Map<GameEvent, EventHandler> initializeEventHandlers() {
        Map<GameEvent, EventHandler> handlers = new HashMap<>();
        EventHandler defaultHandler = new DefaultEventHandler();
        
        // Registra gli handler per ogni evento
        handlers.put(GameEvent.SELECT_SHIP, new SelectShipEventHandler());
        handlers.put(GameEvent.SELECT_ACTION, new SelectActionEventHandler());
        handlers.put(GameEvent.SELECT_POSITION, new SelectPositionEventHandler());
        handlers.put(GameEvent.EXECUTE_ACTION, new ExecuteActionEventHandler());
        handlers.put(GameEvent.SPECIAL_ACTION_SELECTED, new SpecialActionSelectedEventHandler());
        
        // Per gli altri eventi, usa l'handler di default
        handlers.put(GameEvent.SELECT_ARMY, defaultHandler);
        handlers.put(GameEvent.COMPOSE_ARMY, defaultHandler);
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

    @Override
    public void update() {
        GameEvent currentEvent = makeTurn.getCurrentEvent();

        // Gestione speciale per l'inizio del turno
        if (currentEvent == GameEvent.PLAYER1_TURN_STARTED || currentEvent == GameEvent.PLAYER2_TURN_STARTED) {
            System.out.println("=".repeat(25));
            System.out.println(makeTurn.getViewData().getTitle());
            return;
        }
        
        // Visualizzazione standard
        System.out.println("=".repeat(25));
        System.out.println(makeTurn.getViewData().getTitle());
        System.out.println("=".repeat(25));

        boardView.printBoard(makeTurn.getViewData().getBoard(), makeTurn.getViewData().getAreaEffect());

        System.out.println(makeTurn.getViewData().getMessage());
        System.out.println(makeTurn.getViewData().getErrorMessage());

        // Delega la gestione dell'evento all'handler appropriato
        EventHandler handler = eventHandlers.getOrDefault(currentEvent, new DefaultEventHandler());
        handler.handle(makeTurn, selectionView);
    }
    
}
