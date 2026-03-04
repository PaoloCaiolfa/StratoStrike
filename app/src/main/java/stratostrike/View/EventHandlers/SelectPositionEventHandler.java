package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.View.SelectionView;

/**
 * Handler per l'evento SELECT_POSITION.
 * Gestisce la selezione della posizione target da parte del giocatore.
 */
public class SelectPositionEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SelectionView selectionView) {
        selectionView.askForTarget();
    }
}
