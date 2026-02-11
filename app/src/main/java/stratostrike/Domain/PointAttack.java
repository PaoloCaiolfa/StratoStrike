package stratostrike.Domain;

public class PointAttack extends Capability {
    private int damage;

    public PointAttack() {super();}

    public PointAttack(String name, String description, int damage) {
        super(name, description);
        this.damage = damage;
    }

    @Override
    public Action cloneAction() {
        PointAttack clone = new PointAttack(this.name, this.description, this.damage);
        if (this.shape != null) {
            // Clone the shape - you'll need to implement this
            clone.setShape(new Circle(((Circle)this.shape).getRadius()));
        }
        return clone;
    }

    @Override
    public boolean isValidTarget(Board board, Position target, StratoShip actor) {
        // Serve vedere se nella posizione c'è un veicolo nemico
        System.out.println("Controllo validità del target per PointAttack...");
        return true;
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

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("         Descrizione: ").append(description).append("\n");
        details.append("         Danno: ").append(damage).append("\n");
        if (shape != null && shape instanceof Circle) {
            Circle circle = (Circle) shape;
            details.append("         Raggio: ").append(circle.getRadius());
        }
        return details.toString();
    }

}