package stratostrike.View;

import stratostrike.Domain.Model.Action.*;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Settings;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;

import java.util.ArrayList;



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


    public void askForTarget() {
        ArrayList<Integer> targetValue = InputView.getPositionTarget();
        makeTurn.selectTarget(targetValue);
    }

    public void askForContinue() {
        boolean validInput = InputView.readYesOrNo();
        makeTurn.executeAction(validInput);
    }

    public void askForArmy() {
        int selectedArmy = InputView.selectArmy(Settings.ArmyTipology.size() + setupArmy.getArmyNames().size() - 1);
        setupArmy.selectArmy(selectedArmy);
        
    }
}
