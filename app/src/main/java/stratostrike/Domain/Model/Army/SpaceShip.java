package stratostrike.Domain.Model.Army;

public abstract class SpaceShip extends StratoShip {

    public SpaceShip() {}

    public SpaceShip(String name, int hp, int weight) {
        super(name, hp, weight);
    }
}
