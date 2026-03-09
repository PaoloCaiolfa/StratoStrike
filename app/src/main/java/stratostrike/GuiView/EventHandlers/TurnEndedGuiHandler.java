package stratostrike.GuiView.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.GuiView.GameScreenLocal;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.SelectionScreenLocal;

/**
 * Handler per TURN_ENDED.
 * Passa al giocatore successivo e avvia il nuovo turno.
 * La chiamata a playTurn() genera un nuovo ciclo di notifiche verso la GUI.
 */
public class TurnEndedGuiHandler implements GuiEventHandler {

    @Override
    public void handle(
        MakeTurn makeTurn,
        SetupArmy setupArmy,
        LoopingTurn loopingTurn,
        SelectionScreenLocal selectionScreen,
        GameScreenLocal gameScreen,
        NavigationControllerLocal navigator
    ) {
        loopingTurn.nextUITurn();
        makeTurn.playTurn();
    }
}
