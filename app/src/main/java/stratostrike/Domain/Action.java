package stratostrike.Domain;

public interface Action {
    String getName();
    void doAction(Board board, StratoShip target, StratoShip actor);
}
