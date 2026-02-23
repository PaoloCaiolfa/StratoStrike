package stratostrike.Controller;

import java.util.ArrayList;
import java.util.List;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Action.*;
import stratostrike.View.*;
import stratostrike.GameEvent;
import stratostrike.Domain.Model.*;

public class MakeTurn {

    private StratoCraftGame game;
    private ViewData viewData;

    private List<Observer> observers = new ArrayList<>();
   
    public MakeTurn(StratoCraftGame game) {
        this.game = game;    
        this.viewData = new ViewData();
    }

    /** ========== SETUP =========== */

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void updateViewData() {
        viewData.from(game);
    }  

    public void refresh() {
        updateViewData();
        notifyObservers();
    }

    public void playTurn() {
        refresh();
    }

    /** ========== GETTERS ========== */

    public Board getBoard() {
        return game.getBoard();
    }

    public GameEvent getCurrentEvent() {
        return game.getCurrentEvent();
    }

    public ViewData getViewData() {
        return viewData;
    }

    public PlayerInfo getPlayerInfo() {
        Player current = game.getContext().getCurrentPlayer();
        ArrayList<StratoShip> army = current.getArmy().getShips();
        return new PlayerInfo(current,army); 
    }


    /** ========== EVENT HANDLING ========== */

    /**
     * The logic for selecting a ship is handled in the game context, this method just updates the selected ship based on the index of the alive ships of the current player's army and then changes the current event to SELECT_ACTION
     * @param selectedIndex
     */
    public void selectShip(int selectedIndex) {
        Player current = game.getContext().getCurrentPlayer();
        game.getContext().setSelectedShip(current.getArmy().get(selectedIndex));
        game.setCurrentEvent(GameEvent.SELECT_ACTION);

        refresh();
    }

    /**
     * The logic for selecting an action is handled in the game context, this method just updates the selected action based on the index of the available actions of the selected ship and then changes the current event to SELECT_POSITION
     * @param selectedIndex
     */
    public void selectAction(int selectedIndex) {
        StratoShip selectedShip = game.getContext().getSelectedShip();  
        game.getContext().setSelectedAction(selectedShip.getActions().get(selectedIndex));
        game.setCurrentEvent(GameEvent.SELECT_POSITION);

        refresh();
    }


    /**
     * The logic for selecting a target position is handled in the game context, this method just updates the target position based on the coordinates of the selected position and then changes the current event to EXECUTE_ACTION
     * @param target
     */
    public void selectTarget(ArrayList<Integer> target) {
        Position positionTarget = game.getBoard().getPositionByCoordinates(target);
        game.getContext().setTargetPosition(positionTarget);
        game.setCurrentEvent(GameEvent.EXECUTE_ACTION);

        refresh();
    }

    /**
     * Mostra l'area di effetto dell'azione selezionata
     */
    public AffectedPositionsAndArmy showAreaEffect(ArrayList<Integer> target) {
        //List<Integer> target = InputView.getPositionTarget(game.getBoard());

        Position positionTarget = game.getBoard().getPositionByCoordinates(target);
        game.getContext().setTargetPosition(positionTarget);
        ArrayList<Position> affectedPositions = game.getContext().getSelectedAction().getShape().getCoveredCordinates(positionTarget);
        ArrayList<StratoShip> army = game.getContext().getCurrentPlayer().getArmy().getShips();
        //BoardView.showAreaEffect(affectedPositions, game.getBoard(), army);
        return new AffectedPositionsAndArmy(affectedPositions, army);
    }

    /**
     * Esegue l'azione selezionata
     */
    public void executeAction() {
        StratoShip actor = game.getContext().getSelectedShip();
        Action action = game.getContext().getSelectedAction();
        
        ArrayList<Integer> target = InputView.getPositionTarget();
        Position targetPosition = game.getBoard().getPositionByCoordinates(target);
        
        if (action.isValidTarget(game.getBoard(), targetPosition, actor)) {
            action.doAction(game.getBoard(), targetPosition, actor);
        } else {
            executeAction(); // Chiedi di nuovo finché non viene selezionato un target valido
        }
    }
}