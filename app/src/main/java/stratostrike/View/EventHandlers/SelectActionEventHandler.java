package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

/**
 * Handler per l'evento SELECT_ACTION.
 * Gestisce la selezione dell'azione per la nave.
 */
public class SelectActionEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView,
                       GameOutputView gameOutputView, SetupOutputView setupOutputView) {
        selectionView.askForAction(makeTurn.getViewData().getAvailableActions());
    }
}
