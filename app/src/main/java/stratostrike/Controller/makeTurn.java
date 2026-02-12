package stratostrike.Controller;

import java.util.ArrayList;
import java.util.List;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.Domain.Model.Action.*;
import stratostrike.View.*;
import stratostrike.Input.*;
import stratostrike.Domain.Model.*;

public class MakeTurn {

    private StratoCraftGame game;
   
    public MakeTurn(StratoCraftGame game) {
        this.game = game;    
     
    }

    public Board getBoard() {
        return game.getBoard();
    }

    /**
     * Gestisce l'intero turno del giocatore corrente
     */
    public PlayerInfo getPlayerInfo() {
        

        Player current = game.getContext().getCurrentPlayer();
        ArrayList<StratoShip> army = current.getArmy().getShips();
        return new PlayerInfo(current,army); 

    }




    /**
     * Permette al giocatore di selezionare una nave dalla sua armata
     */
    public void selectShip(int selectedIndex) {

        Player current = game.getCurrentPlayer();
        //int selectedIndex = InputView.getShipSelection(current.getArmy());
        game.getContext().setSelectedShip(current.getArmy().get(selectedIndex));
       
        
     
    }

    /**
     * Mostra le azioni disponibili per la nave selezionata
     */
    public void getActions() {
        StratoShip selectedShip = game.getSelectedShip();
        ArrayList<Action> actions = selectedShip.getActions();
       
    }

    /**
     * Permette al giocatore di selezionare un'azione
     */
    public void selectAction(int selectedIndex) {

        StratoShip selectedShip = game.getContext().getSelectedShip();  
        Action selectedAction = selectedShip.getActions().get(selectedIndex);
        game.getContext().setSelectedAction(selectedAction);
  
        
    }

    /**
     * Mostra l'area di effetto dell'azione selezionata
     */
    public AffectedPositionsAndArmy showAreaEffect(ArrayList<Integer> target) {
        //List<Integer> target = InputView.getPositionTarget(game.getBoard());

        Position positionTarget = game.getBoard().getPosition(target.get(0), target.get(1), target.get(2));
        game.getContext().setTargetPosition(positionTarget);
        ArrayList<Position> affectedPositions = game.getSelectedAction().getShape().getCoveredCordinates(positionTarget);
        ArrayList<StratoShip> army = game.getCurrentPlayer().getArmy().getShips();
        //BoardView.showAreaEffect(affectedPositions, game.getBoard(), army);
        return new AffectedPositionsAndArmy(affectedPositions, army);
    }

    /**
     * Esegue l'azione selezionata
     */
    public void executeAction() {
        StratoShip actor = game.getSelectedShip();
        Action action = game.getSelectedAction();
        
        List<Integer> target = InputView.getPositionTarget(game.getBoard());
        Position targetPosition = game.getBoard().getPosition(target.get(0), target.get(1), target.get(2));
        
        if (action.isValidTarget(game.getBoard(), targetPosition, actor)) {
            action.doAction(game.getBoard(), targetPosition, actor);
        } else {
            executeAction(); // Chiedi di nuovo finché non viene selezionato un target valido
        }
    }
}