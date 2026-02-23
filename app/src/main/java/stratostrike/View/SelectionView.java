package stratostrike.View;

import stratostrike.Domain.Model.Action.*;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.*;
import stratostrike.Settings;
import stratostrike.Controller.MakeTurn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SelectionView {

private MakeTurn makeTurn;


    public SelectionView(MakeTurn makeTurn) {
        this.makeTurn = makeTurn;
    }

    /** 
     * Metodo per mostrare le azioni disponibili per la nave selezionata e chiedere un input al giocatore per selezionare un'azione
     */
    public void showActions() {
   
        //ArrayList<Action> actions = makeTurn.showActions();
        //System.out.println("\nAzioni disponibili per la nave ");
        //for (int i = 0; i < actions.size(); i++) {
        //    System.out.println(i + ": " + actions.get(i).getName());
        //}
    }

    /**
     * Metodo per mostrare le azioni disponibili per la nave selezionata e chiedere un input al giocatore per selezionare un'azione
     */
    public void selectAction(){
        
        //ArrayList<Action> actions = makeTurn.showActions();
        //int selectedIndex = InputView.getActionSelection(actions.size());
        //
        //makeTurn.selectAction(selectedIndex);
        //ArrayList<Integer> targetValue = InputView.getPositionTarget();
        //AffectedPositionsAndArmy areaEffectRecord = makeTurn.showAreaEffect(targetValue);
        //BoardView.showAreaEffect(areaEffectRecord, makeTurn.getBoard());

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

        int selectedIndex = InputView.getShipSelection(army.size());
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

        int selectedIndex = InputView.getActionSelection(actions.size());
        makeTurn.selectAction(selectedIndex);
    }


    public void askForTarget() {
        ArrayList<Integer> targetValue = InputView.getPositionTarget();
        makeTurn.selectTarget(targetValue);
    }


        /**
     * Metodo per stampare un'anteprima dell'area di effetto di un'azione
     */
    public static void showAreaEffect(AffectedPositionsAndArmy areaEffectRecord, Board board) {
        Set<Position> areaSet = new HashSet<>(areaEffectRecord.affectedPositions());

        

        for (int z = 0; z < board.getLevels(); z++) {
            System.out.println("\n--- ANTEPRIMA AREA EFFETTO - LIVELLO " + z + " (" + board.getLevelName(z) + ") ---");
            
            // Intestazione X
            System.out.print("    ");
            for (int j = 0; j < board.getWidth(); j++) {
                System.out.print(j + (j < 10 ? "  " : " "));
            }
            System.out.println();

            for (int i = 0; i < board.getLength(); i++) {
                // Coordinata Y
                System.out.print((i < 10 ? " " : "") + i + " |");
                
                for (int j = 0; j < board.getWidth(); j++) {
                    Position pos = board.getPosition(i, j, z);
                    String charToPrint = ".";
                    String color = "";

                    if (areaSet.contains(pos)) {
                        charToPrint = "+";
                    } else if (pos.getShip() != null) {
                        charToPrint = String.valueOf(pos.getShip().getName().charAt(0));
                    }

                    if (pos.getShip() != null) {
                        color = (pos.getShip().getIdArmy() == 0) ? Settings.MINE : Settings.OPPONENT;
                    }

                    System.out.print(color + charToPrint + Settings.RESET + "  ");
                }
                System.out.println("|");
            }
            System.out.println("    " + "-".repeat(board.getWidth() * 3));
        }
    }
}
