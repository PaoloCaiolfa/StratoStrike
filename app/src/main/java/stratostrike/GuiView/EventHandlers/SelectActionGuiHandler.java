package stratostrike.GuiView.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.GuiView.GameScreenLocal;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.SelectionScreenLocal;

/**
 * Handler per SELECT_ACTION.
 * Aggiorna la vista e mostra le azioni disponibili nella sidebar.
 */
public class SelectActionGuiHandler implements GuiEventHandler {

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
        gameScreen.showActionOptions();
    }
}
