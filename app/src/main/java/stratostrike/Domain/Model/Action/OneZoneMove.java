package stratostrike.Domain.Model.Action;


import java.util.ArrayList;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.validate.Validate;


public class OneZoneMove extends Movement {

    public OneZoneMove() {super();}

    public OneZoneMove(String name, String description, Shape shape, ArrayList<Validate> validates) {
        super(name, description, shape, validates);
    }

    @Override
    public Action cloneAction() {
        OneZoneMove clone = new OneZoneMove(this.name, this.description, this.shape, this.validators);
        return clone;
    }

    @Override
    public void doAction(Context context) {
        context.getBoard().moveShip(context.getSelectedShip(), context.getTargetPosition());
    }
    
}
