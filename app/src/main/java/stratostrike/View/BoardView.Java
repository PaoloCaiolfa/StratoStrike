package stratostrike.View;

import stratostrike.Domain.*;
import java.util.ArrayList;

public class BoardView {
    // 1. Le costanti vanno dichiarate QUI, fuori dai metodi
    public static final String OPPONENT = "\u001B[31m"; // Rosso
    public static final String MINE = "\u001B[34m";     // Blu
    public static final String RESET = "\u001B[0m";    // Torna al bianco

    public static void printBoard(Board board) {
        String[] levelNames = {"--- LIVELLO 0: CIELO ---", "--- LIVELLO 1: SPAZIO ---"};
        
        for (int z = 0; z < board.getLevels(); z++) {
            System.out.println("\n" + levelNames[z]);
            
            // Intestazione larghezza (X)
            System.out.print("    "); // Spazio extra per allineare con le righe
            for (int j = 0; j < board.getWidth(); j++) {
            if (j < 10) System.out.print(j+ "  "); // Allineamento numeri colonna
            else{
                System.out.print(j + " ");
                }
            }
            System.out.println();
            
            // Ciclo altezza (Y)
            for (int i = 0; i < board.getLength(); i++) {
                // Allineamento numeri riga
                if (i < 10) System.out.print(" ");
                System.out.print(i + " |");  
                
                for (int j = 0; j < board.getWidth(); j++) {

                    Position pos = board.getPosition(i, j, z);
                    
                    if (pos.getShip() != null) {
                        char shipIcon = pos.getShip().getName().charAt(0);
                        
                        // Applichiamo il colore in base all'ID dell'armata
                        if (pos.getShip().getIdArmy() == 0) {
                            System.out.print(MINE + shipIcon + RESET + "  ");
                        } else {
                            System.out.print(OPPONENT + shipIcon + RESET + "  ");
                        }   
                    } else {
                        System.out.print(".  "); 
                    }
                }
                System.out.println("|");
            }
            System.out.println("    " + "-".repeat(board.getWidth()*3));
        }
    }


    public static void printArmy(ArrayList<StratoShip> army,Board board) {
        System.out.println("Navi nell'armata:");
        System.out.println("le tue navi sono blu, quelle avversarie rosse.");
        for (int i = 0; i < army.size(); i++) {
            StratoShip ship = army.get(i);

            System.out.println(i + ": " + ship.getName() + " (HP: " + ship.getHealth() + ")"+ board.getShipPosition(ship).toString() );
           
        }
    }


    public static void printActualPlayer(Player player) {
        System.out.println("-----------------------------------");
        System.out.println("Turno di: " + player.getUsername());
        System.out.println();
        System.out.println("Seleziona una nave dalla tua armata:");
        System.out.println("-----------------------------------");
    }

}