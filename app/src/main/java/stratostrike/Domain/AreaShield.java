package stratostrike.Domain;

public class AreaShield extends Capability {
    private int protection; //quantità di protezione
    
    public AreaShield() {
        super("","");
    }

    public AreaShield(String name, String description, int protection) {
        super(name, description);
        this.protection = protection;
    }

    @Override
    public boolean isValidTarget(Board board, Position target, StratoShip actor) {
        // Serve vedere se nella posizione c'è un veicolo nemico
        return false;
    }

    @Override
    public void doAction(Board board, Position target,StratoShip actor) {
       
    }
    @Override
    public Shape getShape() {
        return shape;   
    }
    
    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }
}
