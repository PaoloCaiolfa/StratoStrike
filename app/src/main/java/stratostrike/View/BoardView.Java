package stratostrike.View;

import stratostrike.Domain.*;
import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Army.*;

import java.util.ArrayList;

public class BoardView {
    // 1. Le costanti vanno dichiarate QUI, fuori dai metodi
    public static final String OPPONENT = "\u001B[31m"; // Rosso
    public static final String MINE = "\u001B[34m";     // Blu
    public static final String RESET = "\u001B[0m";    // Torna al bianco

    public static void printBoard(Board board) {
       for (int z = 0; z < board.getLevels(); z++) {
       
        System.out.println("\n--- LIVELLO " + z + ": " + board.getLevelName(z) + " ---");
            
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

            System.out.println(i + ": " + ship.getName() + " (HP: " + ship.getHp() + ")"+ board.getShipPosition(ship).toString() );
           
        }
    }


    public static void printActualPlayer(Player player) {
        System.out.println("-----------------------------------");
        System.out.println("Turno di: " + player.getUsername());
        System.out.println();
        System.out.println("Seleziona una nave dalla tua armata:");
        System.out.println("-----------------------------------");
    }


    public static void showAreaEffect(ArrayList<Position> areaEffect, Board board, ArrayList<StratoShip> army) {
        // Usiamo HashSet per performance O(1)
        java.util.Set<Position> areaSet = new java.util.HashSet<>(areaEffect);

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

                    // 1. Decidiamo il carattere (Nave o Area?)
                    if (areaSet.contains(pos)) {
                        charToPrint = "+";
                    } else if (pos.getShip() != null) {
                        charToPrint = String.valueOf(pos.getShip().getName().charAt(0));
                    }

                    // 2. Decidiamo il colore (Solo se c'è una nave o un effetto)
                    if (pos.getShip() != null) {
                        color = (pos.getShip().getIdArmy() == 0) ? MINE : OPPONENT;
                    }

                    // 3. Stampiamo (Colore + Carattere + Reset + Spazi)
                    System.out.print(color + charToPrint + RESET + "  ");
                }
                System.out.println("|");
            }
            System.out.println("    " + "-".repeat(board.getWidth() * 3));
        }
    }
}


