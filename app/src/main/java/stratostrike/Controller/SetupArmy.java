package stratostrike.Controller;
import stratostrike.Domain.Model.Observer;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Army.Factory.ArmyFactory;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.Domain.Model.Army.Factory.ArmyManager;
import stratostrike.Domain.Model.Army.Factory.CustomArmyLoader;

import java.util.ArrayList;
import java.util.List;

import stratostrike.Settings;




public class SetupArmy  {

     private StratoCraftGame game;
     private ArrayList<String> armyNames = new ArrayList<>();
     private ArrayList<Observer> observers = new ArrayList<>();

    public SetupArmy(StratoCraftGame game) {
        this.game = game;
        this.armyNames = CustomArmyLoader.getInstance().getArmyName();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }   

    public void selectionForAllPlayer() {
        for (Player player : game.getPlayers()) {
            System.out.println("\nGiocatore " + player.getUsername() + ", seleziona la tua armata:");
            game.getContext().setCurrentPlayer(player);
            notifyObservers();
        }
    }

    public StratoCraftGame getGame() {
        return game;
    }

    public List<String> getArmyNames() {
        return armyNames;
    }

    public String getPlayerUsername() {
        return game.getContext().getCurrentPlayer().getUsername();
    }
   
    public void selectArmy(int value) {
        if (value < Settings.ArmyTipology.size()) {
            String factoryName = Settings.ArmyTipology.get(value);
            ArmyFactory factory = ArmyManager.getFactory(factoryName);
            Army army1 = factory.createArmy(factoryName);
            game.getContext().getCurrentPlayer().setArmy(army1);
            game.getBoard().setupRandomArmyPlacement(army1);
        } else {
            ArmyFactory factory = ArmyManager.getFactory("CUSTOM");
            Army customArmy = factory.createArmy(armyNames.get(value - Settings.ArmyTipology.size()));
            game.getContext().getCurrentPlayer().setArmy(customArmy);
            game.getBoard().setupRandomArmyPlacement(customArmy);
        }

    }

}
