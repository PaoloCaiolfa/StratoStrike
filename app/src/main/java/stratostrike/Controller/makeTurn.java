package stratostrike.Controller;

import java.util.ArrayList;
 
import stratostrike.Domain.*;
import stratostrike.View.*;
import stratostrike.Input.*;


public class MakeTurn {

    private StratoCraftGame game;


    public MakeTurn(StratoCraftGame game) {
        this.game = game;
       
    }




    public void playTurn() {
      
        Player current = game.getCurrentPlayer();
        BoardView.printActualPlayer(current);

        ArrayList<StratoShip> army = current.getArmy().getShips();

        BoardView.printBoard(game.getBoard());
        BoardView.printArmy(army);

    }

    public void selectShip() {

        Player current = game.getCurrentPlayer();
    
        int selectedIndex = InputView.getShipSelection(current.getArmy());

      
        StratoShip selectedShip = current.getArmy().get(selectedIndex);

       
        game.setSelectedShip(selectedShip);
    }

    public void showActions() {
        StratoShip selectedShip = game.getSelectedShip();

        ArrayList<Action> actions = selectedShip.showActions();

        ActionView.showActions(actions);
        
    }

    public void showAreaEffect() {
        /*
        Action selectedAction = InputView.selectAction();
        game.setSelectedAction(selectedAction);
        Board board = game.getBoard();

        BoardView.showAreaEffect(affectedPositions, board);
        */  
    }

///
}