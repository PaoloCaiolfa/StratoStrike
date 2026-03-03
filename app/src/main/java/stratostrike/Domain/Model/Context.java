package stratostrike.Domain.Model;
import java.util.ArrayList;

import stratostrike.Domain.Model.Action.*;
import stratostrike.Domain.Model.Army.*;



public class Context{
    private Board board;
    private StratoShip selectedShip;
    private Player currentPlayer;
    private Action selectedAction;
    private Position targetPosition;
    private ArrayList<Position> areaEffect;
    private ArrayList<Action> actionControl; // Lista delle azioni eseguite durante il turno, per tenere traccia di quante azioni sono state fatte e quali sono ancora disponibili
    

    
    public Context() {
        this.board = null;
        this.currentPlayer = null;
        this.selectedShip = null;
        this.selectedAction = null;
        this.targetPosition = null;
        this.areaEffect = new ArrayList<>();
        this.actionControl = new ArrayList<>();
    }

    public Board getBoard() {
        return board;
    }

    public StratoShip getSelectedShip() {
        return selectedShip;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Action getSelectedAction() {
        return selectedAction;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setSelectedShip(StratoShip selectedShip) {
        this.selectedShip = selectedShip;
    }   

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setSelectedAction(Action selectedAction) {
        this.selectedAction = selectedAction;
    }

    public Position getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(Position targetPosition) {
        this.targetPosition = targetPosition;
    }

    public ArrayList<Position> getAreaEffect() {
        return areaEffect;
    }
    
    public void setAreaEffect(ArrayList<Position> areaEffect) {
        this.areaEffect = areaEffect;
    }

    public ArrayList<Action> getActionControl() {
        return actionControl;
    }

    public void setActionControl(Action actionControl) {
        
        this.actionControl.add(actionControl);
    }

    public boolean allActionsDone() {
        if (actionControl.size() ==2) return true;  /// RICORDA SONO 3 AZIONI PER TURNOO, cambia quando avrai deciso quante azioni per turno vuoi mettere
        return false;
    }

    public boolean actionAlreadyDone(Action action) {
        for (Action a : actionControl) {
            if (a.getClass().getSuperclass().equals(action.getClass().getSuperclass())) {
                return true;
            }      
        }

        return false;
    }

    public void resetForNewTurn() {
        this.actionControl.clear();
        
    }
    
}