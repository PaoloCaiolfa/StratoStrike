package stratostrike.Domain.Model.Action;

import java.util.ArrayList;
import stratostrike.Domain.Model.Circle;
import stratostrike.Domain.Model.Context;


public class DoubleAttack extends SpecialAbility {

    public DoubleAttack() {super();}

    public DoubleAttack(String name, String description) {
        super(name, description);
    }

    @Override
    public Action cloneAction() {
        DoubleAttack clone = new DoubleAttack(this.name, this.description);
        if (this.shape != null) {
            clone.setShape(new Circle(((Circle)this.shape).getRadius()));
        }
        return clone;
    }

    @Override
    public void doAction(Context context) {

    }
    
}
