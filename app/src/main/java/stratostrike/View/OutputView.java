package stratostrike.View;

import java.util.ArrayList;

import stratostrike.GameEvent;
import stratostrike.Controller.MakeTurn;
import stratostrike.Domain.Model.Observer;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.*;

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

        // NOTA: questo switch è un po' brutto, ma per ora va bene così. In futuro potremmo voler creare dei metodi specifici per ogni evento e chiamarli qui
        // In questo modo evitiamo di avere tutta la logica di visualizzazione sparsa nei vari metodi del controller e la centralizziamo in un unico punto, rendendo più facile la manutenzione e l'estensione del codice in futuro
        switch (makeTurn.getCurrentEvent()) {
            case GameEvent.SELECT_SHIP:
                selectionView.askForShip(makeTurn.getViewData().getAlivePlayerArmy());
                break;
            case GameEvent.SELECT_ACTION:
                selectionView.askForAction(makeTurn.getViewData().getAvailableActions());
                break;
            case GameEvent.SELECT_POSITION:
                selectionView.askForTarget();
                break;
            default:
                // Non fare nulla per altri eventi
                break;
        }
    }
    
}
