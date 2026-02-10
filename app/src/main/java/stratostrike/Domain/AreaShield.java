package stratostrike.Domain;

public class AreaShield extends Capability {
    private int protection; //quantità di protezione

    public AreaShield() {super();}

    public AreaShield(String name, String description, int protection) {
        super(name, description);
        this.protection = protection;
    }

    @Override
    public Action cloneAction() {
        AreaShield clone = new AreaShield(this.name, this.description, this.protection);
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

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("         Descrizione: ").append(description).append("\n");
        details.append("         Protezione: ").append(protection).append("\n");
        if (shape != null && shape instanceof Circle) {
            Circle circle = (Circle) shape;
            details.append("         Raggio: ").append(circle.getRadius());
        }
        return details.toString();
    }
}
