package stratostrike.Domain;

public class PointAttack extends Capability {
    private int damage;

    public PointAttack(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;        
    }

    @Override
    public void doAction(Board board, StratoShip actor, Position target) {
       
    }
    
}