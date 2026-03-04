package stratostrike.Domain.Model.Action;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;
import java.util.ArrayList;
import stratostrike.Domain.Model.Army.StratoShip;

public class ImproveMovement extends SpecialAbility {

    public ImproveMovement() {
        super();
    }

    public ImproveMovement(String name, String description, Shape range, Shape shape, ArrayList<Validate> validators, ArrayList<Validate> activators) {
        super(name, description, range, shape, validators, activators);
    }


    @Override
    public Action cloneAction() {
        ImproveMovement clone = new ImproveMovement(this.name, this.description, this.range, this.shape, this.validators, this.activators);
        return clone;
    }

   
    @Override
    public void doAction(Context context) {
        // settare il range del moviemnto piu grande
        StratoShip selectedShip = context.getSelectedShip();
        Action movementAction = selectedShip.getMovementActions().get(0); // Assuming the ship has at least one movement action
        movementAction.getRange().setRadius(movementAction.getRange().getRadius() + 1); // Increase the radius by 1
    }




    
}
