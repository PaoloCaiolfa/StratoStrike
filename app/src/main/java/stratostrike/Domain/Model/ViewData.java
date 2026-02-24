package stratostrike.Domain.Model;

import java.util.ArrayList;
import java.util.HashMap;

import stratostrike.MyStrings;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Action.Action;

public class ViewData {

    private String title;
    private Board board;
    private String message;
    private String errorMessage;
    private ArrayList<StratoShip> alivePlayerArmy;
    private ArrayList<Action> availableActions;
    private ArrayList<Position> areaEffect;
    private HashMap<String, Object> data;

    public ViewData() {
        this.title = "title null";
        this.board = null;
        this.alivePlayerArmy = new ArrayList<>();
        this.availableActions = new ArrayList<>();
        this.message = "message null";
        this.data = new HashMap<>();
        this.errorMessage="";
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<StratoShip> getAlivePlayerArmy() {
        return alivePlayerArmy;
    }

    public void setAlivePlayerArmy(ArrayList<StratoShip> alivePlayerArmy) {
        this.alivePlayerArmy = alivePlayerArmy;
    }

    public ArrayList<String> getAvailableShipString() {
        ArrayList<String> shipNames = new ArrayList<>();
        for (StratoShip ship : alivePlayerArmy) {
            shipNames.add(ship.getName());
        }
        return shipNames;
    }

    public ArrayList<Action> getAvailableActions() {
        return availableActions;
    }

    public void setAvailableActions(ArrayList<Action> availableActions) {
        this.availableActions = availableActions;
    }

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public ArrayList<Position> getAreaEffect() {
        return areaEffect;
    }

    public void setAreaEffect(ArrayList<Position> areaEffect) {
        this.areaEffect = areaEffect;
    }

    public String getErrorMessage() {
        String e = new String(errorMessage);
        setErrorMessage("");
        return e;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void from(StratoCraftGame game) {
        this.board = game.getBoard();
        this.title = MyStrings.eventTitles.get(game.getCurrentEvent());
        if (game.getContext().getCurrentPlayer() != null) this.alivePlayerArmy = game.getContext().getCurrentPlayer().getArmy().getAliveShips();
        if (game.getContext().getSelectedShip() != null) this.availableActions = game.getContext().getSelectedShip().getActions();
        this.message = MyStrings.eventMessages.get(game.getCurrentEvent());
        this.areaEffect = game.getContext().getAreaEffect();
    }

}
