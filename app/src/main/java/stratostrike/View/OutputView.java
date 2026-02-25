package stratostrike.View;


import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.Domain.Model.Observer;
import stratostrike.GameEvent;


public class OutputView implements Observer {

    MakeTurn makeTurn;
    SetupArmy setupArmy;
    
    BoardView boardView;
    SelectionView selectionView;
  

    public OutputView(MakeTurn makeTurn, SetupArmy setupArmy) {
        this.makeTurn = makeTurn;
        this.setupArmy = setupArmy;
        makeTurn.addObserver(this);
        this.boardView = new BoardView(makeTurn);
        this.selectionView = new SelectionView(makeTurn, setupArmy);
    }

    @Override
    public void update() {

        if (makeTurn.getCurrentEvent() == GameEvent.PLAYER1_TURN_STARTED || makeTurn.getCurrentEvent() == GameEvent.PLAYER2_TURN_STARTED) {
            System.out.println("=".repeat(25));
            System.out.println(makeTurn.getViewData().getTitle());
            return; // Se è l'inizio del turno, non stampare nient'altro per ora
        }
        // Implementazione del metodo update per aggiornare la visualizzazione
        System.out.println("=".repeat(25));
        System.out.println(makeTurn.getViewData().getTitle());
        System.out.println("=".repeat(25));

        boardView.printBoard(makeTurn.getViewData().getBoard(), makeTurn.getViewData().getAreaEffect());

        System.out.println(makeTurn.getViewData().getMessage());

        System.out.println(makeTurn.getViewData().getErrorMessage());

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
            case GameEvent.EXECUTE_ACTION:
                selectionView.askForContinue();
            default:
                // Non fare nulla per altri eventi
                break;
        }
    }
    
}
