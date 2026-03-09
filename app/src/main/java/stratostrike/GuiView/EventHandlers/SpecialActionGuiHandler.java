package stratostrike.GuiView.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.screens.GameScreenLocal;
import stratostrike.GuiView.screens.SelectionScreenLocal;

/**
 * Handler per SPECIAL_ACTION_SELECTED.
 * Aggiorna la vista e mostra il tasto di conferma per l'abilità speciale.
 */
public class SpecialActionGuiHandler implements GuiEventHandler {

    @Override
    public void handle(
        MakeTurn makeTurn,
        SetupArmy setupArmy,
        LoopingTurn loopingTurn,
        SelectionScreenLocal selectionScreen,
        GameScreenLocal gameScreen,
        NavigationControllerLocal navigator
    ) {
        gameScreen.refreshView();
        gameScreen.showConfirmation(() -> {
            makeTurn.detailsForSpecialActionSelected();
            gameScreen.hideConfirmation();
        });
    }
}
