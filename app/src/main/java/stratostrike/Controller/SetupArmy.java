package stratostrike.Controller;
import stratostrike.Domain.Model.Observer;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Army.Factory.ArmyFactory;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.Domain.Model.Army.Factory.ArmyManager;

import java.util.ArrayList;


import stratostrike.Settings;




public class SetupArmy  {

     private StratoCraftGame game;
     private ArrayList<Observer> observers = new ArrayList<>();

    public SetupArmy(StratoCraftGame game) {
        this.game = game;
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

    public String getPlayerUsername() {
        return game.getContext().getCurrentPlayer().getUsername();
    }
   



    public void selectArmy(int value) {
        String factoryName = Settings.ArmyTipology.get(value);
        ArmyFactory factory = ArmyManager.getFactory(factoryName);
        Army army1 = factory.createArmy();
        game.getContext().getCurrentPlayer().setArmy(army1);
        game.getBoard().setupRandomArmyPlacement(army1);
    }

   
    public void selectCustomArmy() {
        
    }

    


 }
