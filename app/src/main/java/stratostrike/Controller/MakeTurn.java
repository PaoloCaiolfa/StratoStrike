package stratostrike.Controller;

import java.util.ArrayList;
import java.util.List;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.Domain.Model.Action.*;
import stratostrike.View.*;
import stratostrike.Domain.Model.*;

public class MakeTurn {

    private StratoCraftGame game;
    private ViewData viewData;

    private List<Observer> observers = new ArrayList<>();
   
    public MakeTurn(StratoCraftGame game) {
        this.game = game;    
        this.viewData = new ViewData();
    }

    public Board getBoard() {
        return game.getBoard();
    }

    public void updateViewData() {
        viewData.from(game);
    }  

    public ViewData getViewData() {
        return viewData;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void playTurn() {
        updateViewData();
        notifyObservers();
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

        Player current = game.getContext().getCurrentPlayer();
        //int selectedIndex = InputView.getShipSelection(current.getArmy());
        game.getContext().setSelectedShip(current.getArmy().get(selectedIndex));
       
        
     
    }

    /**
     * Mostra le azioni disponibili per la nave selezionata
     */
    public ArrayList<Action> showActions() {
        StratoShip selectedShip = game.getContext().getSelectedShip();
        return selectedShip.getActions();
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
        Position targetPosition = game.getBoard().getPosition(target.get(0), target.get(1), target.get(2));
        
        if (action.isValidTarget(game.getBoard(), targetPosition, actor)) {
            action.doAction(game.getBoard(), targetPosition, actor);
        } else {
            executeAction(); // Chiedi di nuovo finché non viene selezionato un target valido
        }
    }
}