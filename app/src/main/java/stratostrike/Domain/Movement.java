package stratostrike.Domain;

public abstract class Movement implements Action {
    protected String name;
    protected String description;
    protected Shape shape;

    public Movement(String name, String description) {
        this.name = name;
        this.description = description;
        this.shape = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public void doAction(Board board, Position target,StratoShip actor) {
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
   