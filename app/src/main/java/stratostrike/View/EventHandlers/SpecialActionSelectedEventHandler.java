package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.View.SelectionView;

/**
 * Handler per l'evento SPECIAL_ACTION_SELECTED.
 * Gestisce la selezione di un'azione speciale da parte del giocatore.
 */
public class SpecialActionSelectedEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SelectionView selectionView) {
        selectionView.askForSpecialActionDetails();
    }
}
