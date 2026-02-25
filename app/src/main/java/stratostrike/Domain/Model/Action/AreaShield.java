package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Circle;
import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.validate.Validate;



public class AreaShield extends Capability {

    private int protection; //quantità di protezione

    public AreaShield() {super();}

    public AreaShield(String name, String description, int protection, Shape shape, Shape range, ArrayList<Validate> valids) {
        super(name, description, shape, range, valids);
        this.protection = protection;
    }

    
    @Override
    public Action cloneAction() {
        AreaShield clone = new AreaShield(this.name, this.description, this.protection, this.shape, this.range, this.validators);
        return clone;
    }

    @Override
    public void doAction(Context context) {
        Board board = context.getBoard();
        Position target = context.getTargetPosition();
        StratoShip actor = context.getSelectedShip();
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