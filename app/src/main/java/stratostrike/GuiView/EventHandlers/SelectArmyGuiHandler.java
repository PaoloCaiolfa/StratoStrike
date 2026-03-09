package stratostrike.GuiView.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.screens.GameScreenLocal;
import stratostrike.GuiView.screens.SelectionScreenLocal;

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
