package stratostrike.Domain.Model;

import java.util.ArrayList;
import java.util.HashMap;

import stratostrike.MyStrings;

public class ViewData {

    private String title;
    private Board board;
    private String message;
    private HashMap<String, Object> data;

    public ViewData() {
        this.title = "title null";
        this.board = null;
        this.message = "message null";
        this.data = new HashMap<>();
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

    public HashMap<String, Object> getData() {
        return data;
    }

    public void setData(HashMap<String, Object> data) {
        this.data = data;
    }

    public void from(StratoCraftGame game) {
        this.board = game.getBoard();
        this.title = "Turno di " + game.getContext().getCurrentPlayer().getUsername();
        this.data.put("playerArmy", game.getContext().getCurrentPlayer().getArmy());
        this.message = MyStrings.eventMessages.get(game.getCurrentEvent());
    }
}
