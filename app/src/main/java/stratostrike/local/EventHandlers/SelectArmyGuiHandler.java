package stratostrike.local.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.local.GameScreenLocal;
import stratostrike.local.NavigationControllerLocal;
import stratostrike.local.SelectionScreenLocal;

/**
 * Handler per gli eventi SELECT_ARMY e COMPOSE_ARMY.
 * Aggiorna il titolo della schermata di selezione con il nome del giocatore corrente.
 */
public class SelectArmyGuiHandler implements GuiEventHandler {

    @Override
    public void handle(
        MakeTurn makeTurn,
        SetupArmy setupArmy,
        LoopingTurn loopingTurn,
        SelectionScreenLocal selectionScreen,
        GameScreenLocal gameScreen,
        NavigationControllerLocal navigator
    ) {
        selectionScreen.refreshTitle();
    }
}
