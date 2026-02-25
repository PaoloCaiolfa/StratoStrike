package stratostrike.Domain.Model.Action;

import stratostrike.Domain.Model.Context;

public class DoubleAttack extends SpecialAbility {

    public DoubleAttack() {super();}

    public DoubleAttack(String name, String description) {
        super(name, description);
    }

    @Override
    public Action cloneAction() {
        DoubleAttack clone = new DoubleAttack(this.name, this.description);
        return clone;
    }

    @Override
    public void doAction(Context context) {

    }
    
}
