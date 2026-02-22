package stratostrike.View;

import stratostrike.Controller.MakeTurn;
import stratostrike.Domain.Model.Observer;

public class OutputView implements Observer {

    MakeTurn makeTurn;
    BoardView boardView;
    SelectionView selectionView;

    public OutputView(MakeTurn makeTurn) {
        this.makeTurn = makeTurn;
        makeTurn.addObserver(this);

        this.boardView = new BoardView(makeTurn);
        this.selectionView = new SelectionView(makeTurn);
    }

    @Override
    public void update() {
        // Implementazione del metodo update per aggiornare la visualizzazione
        System.out.println("=".repeat(25));
        System.out.println(makeTurn.getViewData().getTitle());
        System.out.println("=".repeat(25));

        boardView.printBoard(makeTurn.getViewData().getBoard());

        System.out.println(makeTurn.getViewData().getMessage());
    }
    
}
