package stratostrike.Domain;

public class AreaShield extends Capability {
    private int protection; //quantità di protezione
    
    public AreaShield(String name, String description, int protection) {
        super(name, description);
        this.protection = protection;
    }

    @Override
    public void doAction(Board board, Position target,StratoShip actor) {
       
    }
    @Override
    public Shape getShape() {
        return shape;   
    }
    
}
