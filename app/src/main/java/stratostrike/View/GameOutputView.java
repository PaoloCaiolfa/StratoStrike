package stratostrike.View;

import stratostrike.Controller.MakeTurn;
import stratostrike.GameEvent;

/**
 * Vista responsabile della visualizzazione dello stato del gioco.
 * Si occupa di stampare titolo, board, messaggi ed errori.
 * Non è un Observer - viene chiamata dagli handler quando serve.
 */
public class GameOutputView {
    
    private final BoardView boardView;
    
    public GameOutputView() {
        this.boardView = new BoardView();
    }
    
    /**
     * Visualizza lo stato corrente del gioco.
     * 
     * @param makeTurn il controller con i dati da visualizzare
     */
    public void displayGameState(MakeTurn makeTurn) {
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
    }
}
