package stratostrike.local.EventHandlers;

import stratostrike.Controller.LoopingTurn;
import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.local.GameScreenLocal;
import stratostrike.local.NavigationControllerLocal;
import stratostrike.local.SelectionScreenLocal;

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
