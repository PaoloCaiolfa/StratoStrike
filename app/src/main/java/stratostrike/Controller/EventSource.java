package stratostrike.Controller;

import stratostrike.GameEvent;

/**
 * Interfaccia per i controller che controllano lo stato del gioco.
 * Consente agli observer di recuperare l'evento corrente direttamente dal source.
 */
public interface EventSource {
    /**
     * Restituisce l'evento corrente del gioco.
     * @return l'evento corrente
     */
    GameEvent getCurrentEvent();
}
