package stratostrike.Controller;

import java.util.ArrayList;
import java.util.List;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Action.*;
import stratostrike.GameEvent;
import stratostrike.Domain.Model.*;
import stratostrike.Domain.Model.validate.Validate;
import stratostrike.Domain.Model.validate.ValidationResult;

public class MakeTurn {

    private StratoCraftGame game;
    private ViewData viewData;

    private List<Observer> observers = new ArrayList<>();
   
    public MakeTurn(StratoCraftGame game) {
        this.game = game;    
        this.viewData = new ViewData();
    }

    /** ========== SETUP =========== */

    /**
     * Adds an observer to the list of observers
     * @param observer the observer to be added
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }


    /** 
     * Method to update the view data based on the current state of the game, this method should be called every time the game state changes to ensure that the view data is always up to date with the current state of the game
    */
    public void updateViewData() {
        viewData.from(game);
    }  

    /** 
     * metod to refresh the view by updating the view data and notifying the observers, this method should be called every time the game state changes to ensure that the view is always up to date with the current state of the game
    */
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

        //show range for the selection of target 
        Position shipPosition = game.getBoard().getShipPosition(selectedShip);
        ArrayList<Position> affectedPositions = game.getContext().getSelectedAction().getRange().getCoveredCordinates(shipPosition);
        game.getContext().setAreaEffect(affectedPositions);

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

        ArrayList<Position> affectedPositions = game.getContext().getSelectedAction().getShape().getCoveredCordinates(positionTarget);
        game.getContext().setAreaEffect(affectedPositions);

        refresh();
    }


    /**
     * Esegue l'azione selezionata
     */
    public void executeAction(boolean confirmation) {
      
        Action action = game.getContext().getSelectedAction();
        if (confirmation) {
            ValidationResult result = action.isValidTarget(game.getContext());
            if (result.isValid()) {

                
                
                if (game.getContext().actionAlreadyDone(action)) {
                    viewData.setErrorMessage("Hai già usato un'azione di questo tipo in questo turno, seleziona un'altra azione o termina il turno.");
                    game.setCurrentEvent(GameEvent.SELECT_ACTION);
                }
                else{
                    game.getContext().setActionControl(action);
                    action.doAction(game.getContext());
                    game.setCurrentEvent(GameEvent.SELECT_SHIP);
                    
                    if (game.getContext().allActionsDone()) {
                        game.setCurrentEvent(GameEvent.TURN_ENDED);
                    }
                }
                
            } else {
                viewData.setErrorMessage(result.errorMessage());
                game.setCurrentEvent(GameEvent.SELECT_ACTION);
            }
        } else {
            game.setCurrentEvent(GameEvent.SELECT_ACTION);
        }

        refresh();
    }
}