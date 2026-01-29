package stratostrike.Controller;

public class makeTurn {
    private StratoCraftGame game;

    public makeTurn(StratoCraftGame game) {
        this.game = game;
    }


    public void playTurn() {
        Player current = this.game.getCurrentPlayer();

        ArrayList<StratoShip> ships = current.getArmy();

        BoardView.showArmy(ships);

        this.selectedShip = ships.get(InputView.selectShip());

        ArrayList<Action> allActions = this.selectedShip.getActions();

        StratoShipView.showActions(allActions);
  
        Action selectedAction=allActions.get(InputView.selectAction());


    }
}