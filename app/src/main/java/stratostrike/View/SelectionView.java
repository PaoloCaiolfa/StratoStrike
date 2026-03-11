package stratostrike.View;

import stratostrike.Domain.Model.Action.*;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Settings;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;

import java.util.ArrayList;


/**
 * Classe che si occupa di gestire gli input presi da InputView e smistarli al controllore che si occupa di interpretarli e aggiornare lo stato
 */
public class SelectionView {

    private MakeTurn makeTurn;
    private SetupArmy setupArmy;


    public SelectionView(MakeTurn makeTurn, SetupArmy setupArmy) {
        this.makeTurn = makeTurn;
        this.setupArmy = setupArmy;
    }


    /**
     * Method to ask the player to select a ship from their army and set it as the selected ship in the game context
     * @param army
     */
    public void askForShip(ArrayList<StratoShip> army) {
        System.out.println("\nSeleziona una nave dalla tua armata:");
        for (int i = 0; i < army.size(); i++) {
            StratoShip ship = army.get(i);
            System.out.println(i + ": " + ship.toString());
        }
        System.out.println(army.size() + ": passa il turno");

        int selectedIndex = InputView.getShipSelection(army.size() + 1);
        makeTurn.selectShip(selectedIndex);
    }


    /**
     * Method to ask the player to select an action from the available actions of the selected ship and set it as the selected action in the game context
     * @param actions
     */
    public void askForAction(ArrayList<Action> actions) {
        System.out.println("\nSeleziona un'azione per la nave selezionata:");
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            System.out.println(i + ": " + action.toString());
            
        }
        System.out.println(actions.size() + ": torna selezione nave");

        int selectedIndex = InputView.getActionSelection(actions.size() + 1);
        makeTurn.selectAction(selectedIndex);
    }


    /**
     * Metodo per chiedere al giocatore di inserire le coordinate del bersaglio per l'azione selezionata, mostra le coordinate disponibili e chiede al giocatore di inserire le coordinate X, Y e Z del bersaglio, valida l'input e restituisce le coordinate selezionate in una lista di interi
     */
    public void askForTarget() {
        ArrayList<Integer> targetValue = InputView.getPositionTarget();
        makeTurn.selectTarget(targetValue);
    }

    /**
     * Metodo per chiedere al giocatore se vuole continuare a giocare dopo la fine del turno, chiede al giocatore di inserire Y/N, valida l'input e restituisce un booleano che indica se il giocatore vuole continuare o meno
     */
    public void askForContinue() {
        boolean validInput = InputView.readYesOrNo();
        makeTurn.executeAction(validInput);
    }

    /**
     * Metodo per chiedere al giocatore di selezionare un'armata tra quelle mostrate
     */
    public void askForArmy() {
        int selectedArmy = InputView.readValidInt(0, Settings.ArmyTipology.size() + setupArmy.getArmyNames().size());
        setupArmy.selectArmy(selectedArmy);
    }


    /**
     * Metodo per chiedere al giocatore di selezionare le navi da aggiungere alla composizione personalizzata della sua armata, mostra le navi disponibili e chiede al giocatore di inserire l'ID della nave da aggiungere, valida l'input e aggiorna la composizione dell'armata con la nave selezionata, continua a chiedere finché il giocatore non seleziona l'opzione per terminare la composizione dell'armata
     */
    public void askForArmyComposition() {
        
        int selectedShip = InputView.readValidInt(0, setupArmy.getAvailableStratoShips().size());
        if (selectedShip == setupArmy.getAvailableStratoShips().size()) {
           String armyName = InputView.readArmyName();
           setupArmy.finalizeComposition(armyName);
        }
        else {
            setupArmy.addShipToComposition(selectedShip);
        }
        
    }

    /**
     * Metodo che informa il giocatore di aver selezionato un'abilità speciale
     */
    public void askForSpecialActionDetails() {
        System.out.println("\nHai selezionato un'abilità speciale!");
        makeTurn.detailsForSpecialActionSelected();
    }
}
