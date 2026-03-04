package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.View.SelectionView;

/**
 * Handler per l'evento EXECUTE_ACTION.
 * Gestisce la conferma dell'esecuzione dell'azione.
 */
public class ExecuteActionEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SelectionView selectionView) {
        selectionView.askForContinue();
    }
}
