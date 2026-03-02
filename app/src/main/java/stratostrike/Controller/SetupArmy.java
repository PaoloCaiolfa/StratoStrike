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

import stratostrike.GameEvent;
import stratostrike.Settings;




public class SetupArmy  {

     private StratoCraftGame game;
     private ArrayList<Observer> observers = new ArrayList<>();
     
     private ArrayList<String> armyNames = new ArrayList<>();
     private ArrayList<String> selectedShipsForComposition = new ArrayList<>();
     private int compositionShipWeight = 0;
     

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

    public ArrayList<String> getArmyNames() {
        return armyNames;
    }

    public String getPlayerUsername() {
        return game.getContext().getCurrentPlayer().getUsername();
    }
   
    public void selectArmy(int value) {
        if (value == Settings.ArmyTipology.size() + armyNames.size()) { // utlima opzione: composizione armata personalizzata
            game.setCurrentEvent(GameEvent.COMPOSE_ARMY);
            notifyObservers();  // per aggiornare la view con le opzioni di composizione armata, senza mostrare nuovamente la view della selezione arma non c'è aggiprnamento del view data 

        } else if (value < Settings.ArmyTipology.size()) {
            String factoryName = Settings.ArmyTipology.get(value);
            ArmyFactory factory = ArmyManager.getFactory(factoryName);
            Army army1 = factory.createArmy(factoryName);
            game.getContext().getCurrentPlayer().setArmy(army1);
            game.getBoard().setupRandomArmyPlacement(army1);

            game.setCurrentEvent(GameEvent.SELECT_SHIP);
        }
        else {
            ArmyFactory factory = ArmyManager.getFactory("CUSTOM");
            Army customArmy = factory.createArmy(armyNames.get(value - Settings.ArmyTipology.size()));
            game.getContext().getCurrentPlayer().setArmy(customArmy);
            game.getBoard().setupRandomArmyPlacement(customArmy);

            game.setCurrentEvent(GameEvent.SELECT_SHIP);
        }
    }

    public ArrayList<String> getAvailableStratoShips() {
        return ArmyManager.getAvailableShipsToString();
    }

    public int getCompositionShipWeight() {
        return compositionShipWeight;
    }

    public ArrayList<String> getSelectedShipsForComposition() {
        return selectedShipsForComposition;
    }

    public void addShipToComposition(int selectedShip) {

        if (selectedShip==ArmyManager.getAvailableShipsNames().size()) {
            
            game.setCurrentEvent(GameEvent.SELECT_ARMY);
            return;
        }
        selectedShipsForComposition.add(ArmyManager.getAvailableShipsNames().get(selectedShip));
        compositionShipWeight = ArmyManager.calculateCompositionWeight(selectedShipsForComposition);

        notifyObservers();
    }

    

}
