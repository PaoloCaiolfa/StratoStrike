package stratostrike.View.EventHandlers;

import stratostrike.Controller.MakeTurn;
import stratostrike.View.SelectionView;

/**
 * Interfaccia per gestire gli eventi di gioco.
 * Ogni implementazione definisce come visualizzare e gestire un evento specifico.
 */
public interface EventHandler {
    
    /**
     * Gestisce l'evento corrente.
     * 
     * @param makeTurn il controller del turno corrente
     * @param selectionView la view per le selezioni
     */
    void handle(MakeTurn makeTurn, SelectionView selectionView);
}
