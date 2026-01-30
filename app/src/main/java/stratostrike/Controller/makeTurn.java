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
        BoardView.printArmy(army,game.getBoard());

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

    public void selectAction() {
        StratoShip selectedShip = game.getSelectedShip();

        int selectedIndex = InputView.getActionSelection(selectedShip);

        Action selectedAction = selectedShip.showActions().get(selectedIndex);

        game.setSelectedAction(selectedAction);
    }

    public void showAreaEffect() {
        
        Position target=InputView.getPositionTarget();
        ArrayList<Position> affectedPositions=game.selectAction.getShape().getCoveredCordinates(target);

        BoardView.showAreaEffect(affectedPositions, game.getBoard());
          
    }

///
}