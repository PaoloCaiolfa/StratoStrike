package stratostrike.Domain.Model.Action;


import java.util.ArrayList;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;


public class OneZoneMove extends Movement {

    public OneZoneMove() {super();}

    public OneZoneMove(String name, String description, Shape range, ArrayList<Validate> validates) {
        super(name, description, range, validates);
    }

    @Override
    public Action cloneAction() {
        OneZoneMove clone = new OneZoneMove(this.name, this.description, this.range, this.validators);
        return clone;
    }

    @Override
    public void doAction(Context context) {
        context.getBoard().moveShip(context.getSelectedShip(), context.getTargetPosition());
    }
    
}
