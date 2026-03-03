package stratostrike.Domain.Model.Action;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;
import java.util.ArrayList;

public class ImproveMovement extends SpecialAbility {

    public ImproveMovement() {
        super();
    }

    public ImproveMovement(String name, String description, Shape shape, ArrayList<Validate> validators) {
        super(name, description, shape, validators);
        
    }


    @Override
    public Action cloneAction() {
        ImproveMovement clone = new ImproveMovement(this.name, this.description, this.shape, this.validators);
        return clone;
    }

   
    @Override
    public void doAction(Context context) {
        // settare il range del moviemnto piu grande
        
    }




    
}
