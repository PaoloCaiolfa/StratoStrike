package stratostrike.local.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.local.GameScreenLocal;
import stratostrike.local.NavigationControllerLocal;
import stratostrike.local.SelectionScreenLocal;

/**
 * Handler di default per la GUI: aggiorna board, messaggio e sidebar.
 * Usato per SELECT_SHIP, SELECT_POSITION, PLAYER_TURN_STARTED, SHIP_HIT, ecc.
 */
public class DefaultGuiHandler implements GuiEventHandler {

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
    }
}
