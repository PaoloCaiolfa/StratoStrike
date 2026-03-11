package stratostrike.Controller;

import stratostrike.GameEvent;
import stratostrike.Domain.Model.StratoCraftGame;

public class LoopingTurn {
    
    StratoCraftGame game;
    MakeTurn makeTurn;

    public LoopingTurn(StratoCraftGame game) {
        // Costruttore vuoto
        this.game = game;
        this.makeTurn = new MakeTurn(game);

    }

    public MakeTurn getMakeTurn() {
        return makeTurn;
    }

    /**
     * Avvia il ciclo di gioco, alternando i turni dei giocatori finché la partita non termina.
     */
    public void startMatch(){
        int extracted= (int) (Math.random() * game.getPlayers().size()); // Estrae un numero casuale tra 0 e 1
        if (extracted == 0) {
            game.getContext().setCurrentPlayer(game.getPlayer(0));
        } else {
            game.getContext().setCurrentPlayer(game.getPlayer(1));       

        }
        while(!isGameOver()) {
            if (game.getContext().getCurrentPlayer() == game.getPlayer(0)) {
                game.setCurrentEvent(GameEvent.PLAYER1_TURN_STARTED);
            } else {
                game.setCurrentEvent(GameEvent.PLAYER2_TURN_STARTED);
            }
            makeTurn.playTurn();
            nextTurn();
        }

    }

    /**
     * Avvia il ciclo di gioco in modalità GUI, alternando i turni dei giocatori finché la partita non termina.
     * Il metodo è diverso da start match perché abbiamo aggiornato la logica degli eventi tramite event handler
     * di consequenza non c'è più bisogno di governare i turni tramite loop nel while
     */
    public void startUIMatch() {
        int extracted = (int) (Math.random() * game.getPlayers().size());

        if (extracted == 0) {
            game.getContext().setCurrentPlayer(game.getPlayer(0));
            game.setCurrentEvent(GameEvent.PLAYER1_TURN_STARTED);
        } else {
            game.getContext().setCurrentPlayer(game.getPlayer(1));
            game.setCurrentEvent(GameEvent.PLAYER2_TURN_STARTED);
        }
        makeTurn.playTurn();
    }



    /**
     * Verifica se la partita è terminata.
     * @return true se la partita è terminata, false altrimenti.
     */
    public boolean isGameOver() {
        if (game.getPlayer(0).isFleetDestroyed()) {
            System.out.println("Player 2 wins!");
            return true;
        } else if (game.getPlayer(1).isFleetDestroyed()) {
            System.out.println("Player 1 wins!");
            return true;
        }
        return false;
    }
    

    /**
     * Passa al turno del giocatore successivo. Se il giocatore corrente è il player 1, passa al player 2, e viceversa.
      * Il metodo viene chiamato alla fine di ogni turno per alternare i giocatori. In modalità GUI, invece, l'alternanza dei turni è gestita dagli eventi, quindi questo metodo non viene più utilizzato direttamente, ma la logica di cambio giocatore è comunque incapsulata qui per mantenere la coerenza del controller.
     */
    public void nextTurn() {
        if (game.getContext().getCurrentPlayer() == game.getPlayer(0)) {
            game.getContext().setCurrentPlayer(game.getPlayer(1));
        } else {
            game.getContext().setCurrentPlayer(game.getPlayer(0));
        }
    }

    /**
     * Passa al turno del giocatore successivo in modalità GUI.
     */
    public void nextUITurn() {
        if (game.getContext().getCurrentPlayer() == game.getPlayer(0)) {
            game.getContext().setCurrentPlayer(game.getPlayer(1));
            game.setCurrentEvent(GameEvent.PLAYER2_TURN_STARTED);
        } else {
            game.getContext().setCurrentPlayer(game.getPlayer(0));
            game.setCurrentEvent(GameEvent.PLAYER1_TURN_STARTED);
        }
    }

}
