package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.Controller.SetupArmy;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;

/**
 * Interfaccia per gestire gli eventi di gioco.
 * Tutti gli handler ricevono tutte le dipendenze disponibili.
 */
public interface EventHandler {
    
    /**
     * Gestisce l'evento corrente.
     * Ogni handler usa solo le dipendenze di cui ha bisogno.
     */
    void handle(MakeTurn makeTurn, SetupArmy setupArmy, SelectionView selectionView, 
                GameOutputView gameOutputView, SetupOutputView setupOutputView);
}
