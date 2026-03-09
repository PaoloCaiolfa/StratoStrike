package stratostrike.GuiView.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.GuiView.GameScreenLocal;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.SelectionScreenLocal;

/**
 * Interfaccia per gli handler degli eventi di gioco nella GUI locale.
 * Specchio di {@link stratostrike.View.EventHandlers.EventHandler} ma con dipendenze GUI.
 */
public interface GuiEventHandler {

    /**
     * Gestisce l'evento corrente aggiornando la GUI di conseguenza.
     */
    void handle(
        MakeTurn makeTurn,
        SetupArmy setupArmy,
        LoopingTurn loopingTurn,
        SelectionScreenLocal selectionScreen,
        GameScreenLocal gameScreen,
        NavigationControllerLocal navigator
    );
}
