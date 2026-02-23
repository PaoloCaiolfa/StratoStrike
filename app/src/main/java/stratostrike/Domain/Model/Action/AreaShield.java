package stratostrike.Domain.Model.Action;

import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Circle;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.Army.StratoShip;

public class AreaShield extends Capability {
    private int protection; //quantità di protezione

    public AreaShield() {super();}

    public AreaShield(String name, String description, int protection, Shape shape, Shape range) {
        super(name, description, shape, range);
        this.protection = protection;
    }

    @Override
    public Action cloneAction() {
        AreaShield clone = new AreaShield(this.name, this.description, this.protection, this.shape, this.range);
        if (this.shape != null) {
            clone.setShape(new Circle(((Circle)this.shape).getRadius()));
        }
        if (this.range != null) {
            clone.setRange(new Circle(((Circle)this.range).getRadius()));
        }
        return clone;
    }

    @Override
    public boolean isValidTarget(Board board, Position target, StratoShip actor) {
        StratoShip targetShip = target.getShip();

        if (targetShip != null && board.containsShip(targetShip)) {
            return true;
        }

        return false;
    }

    @Override
    public void doAction(Board board, Position target,StratoShip actor) {
        // ciao
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