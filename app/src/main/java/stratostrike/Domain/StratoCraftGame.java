package stratostrike.Domain;

public class StratoCraftGame {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private StratoShip selectedShip;

    public StratoCraftGame(String player1Name, String player2Name) {
        this.board = new Board(stratostrike.Settings.BoardLengthStandard,
                               stratostrike.Settings.BoardWidthStandard,
                               stratostrike.Settings.BoardLevelsStandard);
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.currentPlayer = player1;
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

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}