package stratostrike.Domain.Model;

import java.util.ArrayList;

public class ViewData {

    private String title;
    private Board board;
    private String message;
    private ArrayList<Object> data;

    public ViewData() {
        this.title = "title null";
        this.board = null;
        this.message = "message null";
        this.data = new ArrayList<>();
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

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
        this.data = data;
    }

    public void from(StratoCraftGame game) {
        this.board = game.getBoard();
        this.message = "Turno di " + game.getContext().getCurrentPlayer().getUsername();
        this.data.addAll(game.getContext().getCurrentPlayer().getArmy().getShips());
    }
}
