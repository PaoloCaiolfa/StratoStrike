package stratostrike.Domain;

public interface Action {

    String getName();

    void doAction(Board board, Position target, StratoShip actor);
    //boolean isValidTarget(Board board, Position target, StratoShip actor);
    Shape getShape();
}
