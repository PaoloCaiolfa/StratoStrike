package stratostrike.View;

import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Settings;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set; 

public class BoardView {

    /**
     * Metodo per stampare il tabellone di gioco con le navi posizionate
     */
    public static void printBoard(Board board) {
       for (int z = 0; z < board.getLevels(); z++) {
       
        System.out.println("\n--- LIVELLO " + z + ": " + board.getLevelName(z) + " ---");
            
        System.out.print("    "); 
        for (int j = 0; j < board.getWidth(); j++) {
            if (j < 10) System.out.print(j+ "  "); 
            else{ System.out.print(j + " "); }
        }
        System.out.println();

        for (int i = 0; i < board.getLength(); i++) {
            if (i < 10) System.out.print(" ");
            System.out.print(i + " |");  
                
            for (int j = 0; j < board.getWidth(); j++) {
                Position pos = board.getPosition(i, j, z);
                
                if (pos.getShip() != null) {
                    char shipIcon = pos.getShip().getName().charAt(0);
                    if (pos.getShip().isDestroyed()) {
                        shipIcon = 'X';
                    }
                    
                    if (pos.getShip().getIdArmy() == 0) {
                        System.out.print(Settings.MINE + shipIcon + Settings.RESET + "  ");
                    } else {
                        System.out.print(Settings.OPPONENT + shipIcon + Settings.RESET + "  ");
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

    /**
     * Metodo per stampare le navi dell'armata con le loro posizioni
     */
    public static void printArmy(ArrayList<StratoShip> army,Board board) {
        System.out.println("Navi nell'armata:");
        System.out.println("le tue navi sono blu, quelle avversarie rosse.");
        for (int i = 0; i < army.size(); i++) {
            StratoShip ship = army.get(i);

            System.out.println(i + ": " + ship.getName() + " (HP: " + ship.getHp() + ")"+ board.getShipPosition(ship).toString() );
           
        }
    }

    /**
     * Metodo per stampare il giocatore attivo
     */
    public static void printActualPlayer(Player player) {
        System.out.println("-----------------------------------");
        System.out.println("Turno di: " + player.getUsername());
        System.out.println();
        System.out.println("Seleziona una nave dalla tua armata:");
        System.out.println("-----------------------------------");
    }

    /**
     * Metodo per stampare un'anteprima dell'area di effetto di un'azione
     */
    public static void showAreaEffect(ArrayList<Position> areaEffect, Board board, ArrayList<StratoShip> army) {
        Set<Position> areaSet = new HashSet<>(areaEffect);

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


