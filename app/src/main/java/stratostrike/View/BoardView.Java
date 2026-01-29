package stratostrike.View;

import stratostrike.Domain.*;
import java.util.ArrayList;

public class BoardView {

    public void printBoard(Board board) {
        String[] levelNames = {"--- LIVELLO 0: CIELO ---", "--- LIVELLO 1: SPAZIO ---"};
        
        for (int z = 0; z < board.getLevels(); z++) {
            System.out.println("\n" + levelNames[z]);
            
            //j esplora in larghezza 
            System.out.print("   ");
            for (int j = 0; j < board.getWidth(); j++) System.out.print(j + " ");
            System.out.println();
            
            //i esplora in altezza
            for (int i = 0; i < board.getLength(); i++) {
               
                System.out.print(i + " |");  
                
                for (int j = 0; j < board.getWidth(); j++) {
                    Position pos = board.getPosition(i, j, z);
                    
                    if (pos.getShip() != null) {
                        // Se c'è una nave, stampiamo 'F' per Fighter, 'S' per Satellite, ecc.
                        char shipIcon = pos.getShip().getName().charAt(0);
                        System.out.print(shipIcon + " ");
                    } else {
                        // Se è vuoto, usiamo un simbolo neutro
                        System.out.print(". "); 
                    }
                }
                System.out.println("|");
            }
            System.out.println("   " + "-".repeat(board.getWidth() * 2));
        }
    }

    public static void printArmy(ArrayList<StratoShip> army) {
        System.out.println("Navi nell'armata:");
        for (int i = 0; i < army.size(); i++) {
            StratoShip ship = army.get(i);
            System.out.println(i + ": " + ship.getName() + " (HP: " + ship.getHealth() + ")");
        }
    }

}