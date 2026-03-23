package stratostrike.Domain.Model;

import stratostrike.Controller.EventSource;

/**
 * Interfaccia per implementare il pattern Observer.
 * Il source è un EventSource che fornisce l'evento corrente.
 */
public interface Observer {
    /**
     * Chiamato quando lo stato del subject cambia.
     * @param source il controller (EventSource) che ha notificato il cambio
     */
    void update(EventSource source);
}
