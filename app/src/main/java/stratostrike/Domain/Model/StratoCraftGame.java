package stratostrike.Domain.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

import stratostrike.GameEvent;

public class StratoCraftGame {
    private Board board;
    private ArrayList<Player> players = new ArrayList<>();
    private GameEvent currentEvent;
    private Context context;

    public StratoCraftGame(ArrayList<Player> players) {
        this.players = players;
        this.board = new Board(stratostrike.Settings.BoardLengthStandard,
                               stratostrike.Settings.BoardWidthStandard,
                               stratostrike.Settings.BoardLevelsStandard);
       
        this.currentEvent = GameEvent.SELECT_SHIP;
        this.context = new Context();
        this.context.setCurrentPlayer(players.get(0)); // Il giocatore 1 inizia per primo
        this.context.setBoard(this.board);
    }

    public void setBoard(Board board) {
        this.board = board;
        
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }
    

    public Context getContext() {
        return context;
    }   

    public GameEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(GameEvent currentEvent) {
        context.setAreaEffect(new ArrayList<>());
        this.currentEvent = currentEvent;
    }

}