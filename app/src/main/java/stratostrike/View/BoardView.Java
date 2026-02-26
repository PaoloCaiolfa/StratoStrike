package stratostrike.View;

import stratostrike.Domain.Model.*;
import stratostrike.Settings;
import java.util.ArrayList;

public class BoardView {
    /**
     * Metodo per stampare il tabellone di gioco con le navi posizionate
     */
    public void printBoard(Board board, ArrayList<Position> coveredZone) {
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
                    String charToPrint = ".";
                    String color = "";

                    if (coveredZone.size() > 0 && coveredZone.contains(pos)) {
                        charToPrint = "+";
                    } else if (pos.getShip() != null) {
                        boolean isDestroyed = pos.getShip().isDestroyed();
                        charToPrint = isDestroyed ? "X" : String.valueOf(pos.getShip().getName().charAt(0));  
                    } 

                    if (pos.getShip() != null) {
                        color = (pos.getShip().getIdArmy() == 0) ? Settings.MINE : Settings.OPPONENT;
                    }
                    System.out.print(color + charToPrint + Settings.RESET + "  ");
                }
                System.out.println("|");
            }
            System.out.println("    " + "-".repeat(board.getWidth()*3));
        }
    }

 

/** 


    public void printArmy() {
        PlayerInfo currentInfo = makeTurn.getPlayerInfo();
        ArrayList<StratoShip> armyList = currentInfo.army();
        Board board = makeTurn.getViewData().getBoard();
        
        System.out.println("Navi nell'armata:");
        System.out.println("le tue navi sono blu, quelle avversarie rosse.");
        for (int i = 0; i < armyList.size(); i++) {
            StratoShip ship = armyList.get(i);

            System.out.println(i + ": " + ship.getName() + " (HP: " + ship.getHp() + ")"+ board.getShipPosition(ship).toString() );
           
        }
    }




    public void printActualPlayer() {
        PlayerInfo currentInfo = makeTurn.getPlayerInfo();
        Player player = currentInfo.player();
        System.out.println("-----------------------------------");
        System.out.println("Turno di: " + player.getUsername());
        System.out.println();
        System.out.println("Seleziona una nave dalla tua armata:");
        System.out.println("-----------------------------------");
    }

*/
}


