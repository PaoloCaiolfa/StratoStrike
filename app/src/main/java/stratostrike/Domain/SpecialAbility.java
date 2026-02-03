package stratostrike.Domain;

public abstract class SpecialAbility implements Action {
    protected String name;
    protected String description;
    protected Shape shape;

    public SpecialAbility(String name, String description) {
        this.name = name;
        this.description = description;
        this.shape = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void doAction(Board board, Position target, StratoShip actor) {
        // Default implementation (can be overridden by subclasses)
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
