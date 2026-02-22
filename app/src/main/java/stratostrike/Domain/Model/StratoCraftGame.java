package stratostrike.Domain.Model;

import stratostrike.GameEvent;

public class StratoCraftGame {
    private Board board;
    private Player player1;
    private Player player2;
    private GameEvent currentEvent;
    private Context context;

    public StratoCraftGame(Player player1, Player player2) {
        this.board = new Board(stratostrike.Settings.BoardLengthStandard,
                               stratostrike.Settings.BoardWidthStandard,
                               stratostrike.Settings.BoardLevelsStandard);
        this.player1 = player1;
        this.player2 = player2;
        this.currentEvent = GameEvent.SELECT_SHIP;
        this.context = new Context();
        this.context.setCurrentPlayer(player1); // Il giocatore 1 inizia per primo
        
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Context getContext() {
        return context;
    }   

    public GameEvent getCurrentEvent() {
        return currentEvent;
    }

    public void setCurrentEvent(GameEvent currentEvent) {
        this.currentEvent = currentEvent;
    }

}