package stratostrike.View;

import java.util.ArrayList;
import stratostrike.Domain.Action;  
public class ActionView {

    public static void showActions(ArrayList<Action> actions) {
        System.out.println();
        System.out.println("Azioni disponibili:");
        for (int i = 0; i < actions.size(); i++) {
            System.out.println(i + ": " + actions.get(i).getName().toString());
        }
    }
    
}