package stratostrike.View;

import stratostrike.Domain.Board;
import stratostrike.Domain.StratoShip;
import java.util.ArrayList;

public class BoardView {

    public void printBoard(Board board) {
        int length = board.getLength();
        int width = board.getWidth();
        int levels = board.getLevels();
        for (int a=0; a<2; a++) {
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < width; j++) {
                    System.out.print(board.getPosition(i, j, a).toString() + " "); 
                    


                }
                System.out.println();
            }
        }
    }
    public static void printArmy(ArrayList<StratoShip> army) {
        for (StratoShip ship : army) {
            System.out.println(ship.getName().toString());
        }
    }
}