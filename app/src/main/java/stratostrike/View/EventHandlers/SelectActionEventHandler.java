package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.View.SelectionView;

/**
 * Handler per l'evento SELECT_ACTION.
 * Gestisce la selezione dell'azione da parte del giocatore.
 */
public class SelectActionEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SelectionView selectionView) {
        selectionView.askForAction(makeTurn.getViewData().getAvailableActions());
    }
}
