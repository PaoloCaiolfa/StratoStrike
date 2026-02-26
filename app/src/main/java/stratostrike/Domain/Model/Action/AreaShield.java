package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Circle;
import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.validate.Validate;
import stratostrike.Domain.Model.Position;



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
        Position target = context.getTargetPosition();
        System.out.println("Scudo attivato! Protezione fornita: " + protection);
        int friendlyShipsID = context.getCurrentPlayer().getArmy().getIdArmy();
        ArrayList<Position> coveredPositions = shape.getCoveredCordinates(target); //ottenere le posizioni coperte dalla shape 

        for (Position pos: coveredPositions) {
            StratoShip ship = pos.getShip();
            if (ship != null && ship.getIdArmy() == friendlyShipsID && ship.isDestroyed() == false) {
                ship.repair(protection);
                    System.out.println("La nave " + ship.getName() + " ha ricevuto protezione! HP attuali: " + ship.getHp());
            }
        }
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