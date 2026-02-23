package stratostrike.Domain.Model;
import java.util.ArrayList;

import stratostrike.Domain.Model.*;
import stratostrike.Domain.Model.Action.*;
import stratostrike.Domain.Model.Army.*;



public class Context{
    private Board board;
    private StratoShip selectedShip;
    private Player currentPlayer;
    private Action selectedAction;
    private Position targetPosition;
    private ArrayList<Position> areaEffect;
    

    
    public Context() {
        this.currentPlayer = null;
        this.selectedShip = null;
        this.selectedAction = null;
        this.targetPosition = null;
        this.areaEffect = new ArrayList<>();
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

}