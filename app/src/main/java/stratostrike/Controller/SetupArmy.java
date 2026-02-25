package stratostrike.Controller;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Army.Factory.ArmyFactory;
import stratostrike.Domain.Model.StratoCraftGame;

public class SetupArmy {
    private StratoCraftGame game;

    public void selectArmy(ArmyFactory factory) {
        Army army1 = factory.createArmy();
        Player current = game.getContext().getCurrentPlayer();
        current.setArmy(army1);
    }

 }
