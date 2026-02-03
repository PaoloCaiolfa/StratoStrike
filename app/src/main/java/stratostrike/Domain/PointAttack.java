package stratostrike.Domain;

public class PointAttack extends Capability {
    private int damage;

    public PointAttack() {
        super("", "");
    }

    public PointAttack(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    @Override
    public boolean isValidTarget(Board board, Position target, StratoShip actor) {
        // Serve vedere se nella posizione c'è un veicolo nemico
        return false;
    }

    @Override
    public void doAction(Board board, Position target, StratoShip actor) {

    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}