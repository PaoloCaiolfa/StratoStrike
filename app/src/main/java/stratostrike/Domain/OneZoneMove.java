package stratostrike.Domain;

public class OneZoneMove extends Movement {
    protected Position targetPosition;

    public OneZoneMove() {super();}

    public OneZoneMove(String name, String description) {
        super(name, description);
        this.targetPosition = null;
    }

    @Override
    public Action cloneAction() {
        OneZoneMove clone = new OneZoneMove(this.name, this.description);
        if (this.shape != null) {
            clone.setShape(new Circle(((Circle)this.shape).getRadius()));
        }
        return clone;
    }

    @Override
    public boolean isValidTarget(Board board, Position target, StratoShip actor) {
        // Serve vedere se nella posizione c'è un veicolo nemico
        return false;
    }

    @Override
    public void doAction(Board board, Position target, StratoShip actor) {

    }
    
}
