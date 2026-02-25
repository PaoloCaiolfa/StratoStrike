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
    

    public void nextTurn() {
        if (game.getContext().getCurrentPlayer() == game.getPlayer(0)) {
            game.getContext().setCurrentPlayer(game.getPlayer(1));
        } else {
            game.getContext().setCurrentPlayer(game.getPlayer(0));
        }
    }






}
