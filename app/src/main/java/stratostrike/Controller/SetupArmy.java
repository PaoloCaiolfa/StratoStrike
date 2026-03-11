package stratostrike.Controller;
import java.util.ArrayList;
import java.util.List;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.Factory.ArmyFactory;
import stratostrike.Domain.Model.Army.Factory.ArmyManager;
import stratostrike.Domain.Model.Army.Factory.CustomArmyLoader;
import stratostrike.Domain.Model.Observer;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.StratoCraftGame;
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

    /* =================== OBSERVER SETUP ==================== */

    /**
     * Aggiunge un observer alla lista degli observer, gli observer sono i componenti della view che si registrano per essere notificati ogni volta che lo stato del gioco cambia, in modo da poter aggiornare la view di conseguenza
     * @param observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifica tutti gli observer della modifica dello stato del gioco
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }   

    /* =================== GETTERS ==================== */

    public StratoCraftGame getGame() {
        return game;
    }

    public ArrayList<String> getArmyNames() {
        return armyNames;
    }

    public String getPlayerUsername() {
        return game.getContext().getCurrentPlayer().getUsername();
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

    /* =================== EVENT METHODS ==================== */

    /**
     * Selezione dell'armata per tutti i giocatori
     */
    public void selectionForAllPlayer() {
        for (Player player : game.getPlayers()) {
            System.out.println("\nGiocatore " + player.getUsername() + ", seleziona la tua armata:");
            game.getContext().setCurrentPlayer(player);

            notifyObservers();
        }
    }

    /**
     * Passa alla selezione del giocatore successivo
     */
    public void nextPlayerSelection() {
        List<Player> players = game.getPlayers();
        Player current = game.getContext().getCurrentPlayer();
        int currentIndex = players.indexOf(current);

        if (currentIndex == players.size() - 1) {
            game.setCurrentEvent(GameEvent.SELECT_SHIP);
            notifyObservers();
            return;
        }

        game.getContext().setCurrentPlayer(players.get(currentIndex + 1));
        notifyObservers();
    }


    /**
     * Interpreta il valore dell'indice scelto dal giocatore instanziando la factory corrispondente
     * @param value
     */
    public void selectArmy(int value) {
        // Composizione armata personalizzata
        if (value == Settings.ArmyTipology.size() + armyNames.size()) { 
            game.setCurrentEvent(GameEvent.COMPOSE_ARMY);
            notifyObservers();  
        
        // scelta armata default
        } else if (value < Settings.ArmyTipology.size()) {
            String factoryName = Settings.ArmyTipology.get(value);
            ArmyFactory factory = ArmyManager.getFactory(factoryName);
            Army army1 = factory.createArmy(factoryName);
            game.getContext().getCurrentPlayer().setArmy(army1);
            game.getBoard().setupRandomArmyPlacement(army1);

            game.setCurrentEvent(GameEvent.SELECT_ARMY);
        }
        // scelta armata personalizzata
        else {
            ArmyFactory factory = ArmyManager.getFactory("CUSTOM");
            Army customArmy = factory.createArmy(armyNames.get(value - Settings.ArmyTipology.size()));
            game.getContext().getCurrentPlayer().setArmy(customArmy);
            game.getBoard().setupRandomArmyPlacement(customArmy);

            game.setCurrentEvent(GameEvent.SELECT_ARMY);
        }

        nextPlayerSelection();
    }

    /* =================== CUSTOM ARMY COMPOSITION METHODS ==================== */

    /**
     * Aggiunge una nave alla composizione personalizzata, viene verificato che il peso totale della composizione non superi il limite consentito, in caso contrario viene mostrato un messaggio di errore e la nave non viene aggiunta alla composizione
     * @param selectedShip
     */
    public void addShipToComposition(int selectedShip) {

        selectedShipsForComposition.add(ArmyManager.getAvailableShipsNames().get(selectedShip));
        compositionShipWeight = ArmyManager.calculateCompositionWeight(selectedShipsForComposition);

        notifyObservers();
    }

    /**
     * Finalizza la composizione personalizzata, salvando la nuova armata e selezionandola per il giocatore corrente
     * @param armyName
     */
    public void finalizeComposition(String armyName){
        CustomArmyLoader.getInstance().saveNewArmy(armyName, selectedShipsForComposition);

        armyNames= CustomArmyLoader.getInstance().getArmyName();
        selectedShipsForComposition.clear();
        compositionShipWeight = 0;

        int newArmyIndex = Settings.ArmyTipology.size() + armyNames.indexOf(armyName);
        selectArmy(newArmyIndex);
        
    }

}
