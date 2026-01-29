package stratostrike.Domain;

public class StratoCraftGame {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private StratoShip selectedShip;
    private Action selectedAction;

    public StratoCraftGame(Player player1, Player player2) {
        this.board = new Board(stratostrike.Settings.BoardLengthStandard,
                               stratostrike.Settings.BoardWidthStandard,
                               stratostrike.Settings.BoardLevelsStandard);
        this.player1 = player1;
        this.player2 = player2;
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
    public void setSelectedShip(StratoShip ship) {
        this.selectedShip = ship;
    }
    public StratoShip getSelectedShip() {
        return selectedShip;
    }   
    public void setCurrentPlayer(Player player) {
        this.currentPlayer = player;
    }
    public void setSelectedAction(Action action) {
        this.selectedAction = action;
    }
    public Action getSelectedAction() {
        return selectedAction;  
    }



 


}