package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

/**
 * Handler per l'evento COMPOSE_ARMY.
 * Gestisce la composizione personalizzata dell'armata.
 */
public class ComposeArmyEventHandler implements EventHandler {
    
    @Override
    public void handle(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView,
                       GameOutputView gameOutputView, SetupOutputView setupOutputView) {
        setupOutputView.displayArmyComposition(setupArmy);
        selectionView.askForArmyComposition();
    }
}
