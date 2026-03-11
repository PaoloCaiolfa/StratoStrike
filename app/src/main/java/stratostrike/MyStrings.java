package stratostrike;

import java.util.Map;

public class MyStrings {
    
    /**
     * Stringhe per la visualizzazione dei messaggi relativi agli eventi di gioco
     */
    public static final Map<GameEvent, String> eventMessages = Map.ofEntries(

        Map.entry(GameEvent.TURN_STARTED, "Il turno è iniziato!"),
        Map.entry(GameEvent.PLAYER1_TURN_STARTED, "Turno del Giocatore 1"),
        Map.entry(GameEvent.PLAYER2_TURN_STARTED, "Turno del Giocatore 2"),
        Map.entry(GameEvent.SELECT_SHIP, "Seleziona una nave da utilizzare."),
        Map.entry(GameEvent.SELECT_ACTION, "Seleziona un'azione da eseguire."),
        Map.entry(GameEvent.SELECT_POSITION, "Seleziona una posizione bersaglio."),
        Map.entry(GameEvent.EXECUTE_ACTION, "Confermi le scelte effettuate nel turno? (y or n)"),
        Map.entry(GameEvent.SPECIAL_ACTION_SELECTED, "Abilità speciale selezionata. Confermi l'attivazione?"),
        Map.entry(GameEvent.SHIP_HIT, "Hai colpito una nave nemica!"),
        Map.entry(GameEvent.SHIP_MISSED, "Hai mancato il bersaglio!"),
        Map.entry(GameEvent.SHIP_DESTROYED, "La tua nave è stata distrutta!"),
        Map.entry(GameEvent.TURN_ENDED, "Il turno è terminato."),
        Map.entry(GameEvent.GAME_WON, "Hai vinto la partita!"),
        Map.entry(GameEvent.GAME_LOST, "Hai perso la partita!")
    );


    /**
     * Stringhe per la visualizzazione di titoli per contestualizzare l'input richiesto al giocatore in base all'evento di gioco corrente
     */
    public static final Map<GameEvent, String> eventTitles = Map.ofEntries(
        Map.entry(GameEvent.TURN_STARTED, "Inizio del turno"),
        Map.entry(GameEvent.PLAYER1_TURN_STARTED, "Inizio del turno del giocatore 1"),
        Map.entry(GameEvent.PLAYER2_TURN_STARTED, "Inizio del turno del giocatore 2"),
        Map.entry(GameEvent.SELECT_SHIP, "Selezione della nave"),
        Map.entry(GameEvent.SELECT_ACTION, "Selezione dell'azione"),
        Map.entry(GameEvent.SELECT_POSITION, "Selezione del bersaglio"),
        Map.entry(GameEvent.EXECUTE_ACTION, "Esecuzione dell'azione"),
        Map.entry(GameEvent.SPECIAL_ACTION_SELECTED, "Abilità speciale"),
        Map.entry(GameEvent.SHIP_HIT, "Colpo riuscito"),
        Map.entry(GameEvent.SHIP_MISSED, "Colpo mancato"),
        Map.entry(GameEvent.SHIP_DESTROYED, "Nave distrutta"),
        Map.entry(GameEvent.TURN_ENDED, "Fine del turno"),
        Map.entry(GameEvent.GAME_WON, "Vittoria!"),
        Map.entry(GameEvent.GAME_LOST, "Sconfitta!")
    );
    
}
