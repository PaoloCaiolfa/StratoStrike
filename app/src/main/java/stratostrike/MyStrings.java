package stratostrike;

import java.util.Map;

public class MyStrings {
    
    public static final Map<GameEvent, String> eventMessages = Map.ofEntries(
        Map.entry(GameEvent.TURN_STARTED, "Il turno è iniziato!"),
        Map.entry(GameEvent.SELECT_SHIP, "Seleziona una nave da utilizzare."),
        Map.entry(GameEvent.SELECT_ACTION, "Seleziona un'azione da eseguire."),
        Map.entry(GameEvent.SHIP_HIT, "Hai colpito una nave nemica!"),
        Map.entry(GameEvent.SHIP_MISSED, "Hai mancato il bersaglio!"),
        Map.entry(GameEvent.SHIP_DESTROYED, "La tua nave è stata distrutta!"),
        Map.entry(GameEvent.TURN_ENDED, "Il turno è terminato."),
        Map.entry(GameEvent.GAME_WON, "Hai vinto la partita!"),
        Map.entry(GameEvent.GAME_LOST, "Hai perso la partita!")
    );
    
}
