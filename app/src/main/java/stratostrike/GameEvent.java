package stratostrike;

/**
 * Enum che rappresenta i vari eventi di gioco, utilizzato per gestire lo stato del gioco e aggiornare la view di conseguenza, ogni volta che lo stato del gioco cambia, viene impostato un nuovo evento corrente, e tutti gli observer registrati vengono notificati per aggiornare la view in base al nuovo stato del gioco
 */
public enum GameEvent {
    SELECT_ARMY,
    COMPOSE_ARMY,
    TURN_STARTED,
    SELECT_SHIP,
    SELECT_ACTION,
    SELECT_POSITION,
    EXECUTE_ACTION,  
    SHIP_DESTROYED,
    SHIP_HIT,
    SHIP_MISSED,
    TURN_ENDED,
    GAME_WON,
    GAME_LOST,
    PLAYER1_TURN_STARTED,
    PLAYER2_TURN_STARTED,
    SPECIAL_ACTION_SELECTED
}
