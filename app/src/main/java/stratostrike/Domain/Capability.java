package stratostrike.Domain;
public abstract class Capability implements Action {
    protected String name;
    protected String description;

    public Capability(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public void doAction(Board board, StratoShip actor, StratoShip target) {
        // Default implementation (can be overridden by subclasses)
    }



}