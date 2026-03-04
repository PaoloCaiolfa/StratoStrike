package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

/**
 * Handler di default per eventi che non richiedono azioni particolari.
 */
public class DefaultEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView,
                       GameOutputView gameOutputView, SetupOutputView setupOutputView) {
        // Non fa nulla, serve solo per eventi che non necessitano di interazione
    }
}
