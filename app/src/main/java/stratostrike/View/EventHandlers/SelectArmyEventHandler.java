package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

/**
 * Handler per l'evento SELECT_ARMY.
 * Gestisce la visualizzazione e selezione dell'armata.
 */
public class SelectArmyEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView,
                       GameOutputView gameOutputView, SetupOutputView setupOutputView) {
        setupOutputView.displayArmySelection(setupArmy);
        selectionView.askForArmy();
    }
}
