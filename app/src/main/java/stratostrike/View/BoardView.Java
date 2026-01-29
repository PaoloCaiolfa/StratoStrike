public class BoardView {
    public void printBoard(Board board) {
        int length = stratostrike.Settings.BoardLength;
        int width = stratostrike.Settings.BoardWidth;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board.getCell(i, j).toString() + " "); 


            }
            System.out.println();
        }
    }

}