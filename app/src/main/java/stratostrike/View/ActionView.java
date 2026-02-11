package stratostrike.View;

import java.util.ArrayList;

import stratostrike.Domain.Model.Action.Action;  
public class ActionView {

    public static void showActions(ArrayList<Action> actions) {
        System.out.println();
        System.out.println("Azioni disponibili:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ": " + actions.get(i).getName());
        }
    }

    public static void showActionsWithDetails(ArrayList<Action> actions) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("AZIONI DISPONIBILI");
        System.out.println("=".repeat(60));
        
        for (int i = 0; i < actions.size(); i++) {
            Action action = actions.get(i);
            System.out.println("\n" + i + ") " + action.getName());
            System.out.print(action.getDetails());
        }
        System.out.println();
    }

    public static void showSelectedAction (Action action) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("AZIONE SELEZIONATA: " + action.getName());
        System.out.println("=".repeat(60));
        System.out.print(action.getDetails());
        System.out.println("=".repeat(60));
    }
    
}