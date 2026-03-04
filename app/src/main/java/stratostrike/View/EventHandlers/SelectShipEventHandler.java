package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.View.SelectionView;

/**
 * Handler per l'evento SELECT_SHIP.
 * Gestisce la selezione della nave da parte del giocatore.
 */
public class SelectShipEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SelectionView selectionView) {
        selectionView.askForShip(makeTurn.getViewData().getAlivePlayerArmy());
    }
}
