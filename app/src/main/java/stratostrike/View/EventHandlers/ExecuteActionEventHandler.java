package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

/**
 * Handler per l'evento EXECUTE_ACTION.
 * Gestisce l'esecuzione dell'azione selezionata.
 */
public class ExecuteActionEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView,
                       GameOutputView gameOutputView, SetupOutputView setupOutputView) {
        selectionView.askForContinue();
    }
}
