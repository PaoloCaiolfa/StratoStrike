package stratostrike.Domain.Model.Army;

import stratostrike.Domain.Model.Action.Action;

public class Fighter extends SkyShip {

    public Fighter() {}

    @Override
    public StratoShip cloneShip() {
        Fighter clone = new Fighter();
        clone.setName(this.name);
        clone.setHp(this.hp);
        clone.setIdArmy(this.idArmy);
        
        // Clone actions
        for (Action action : this.actions) {
            clone.addAction(action.cloneAction());
        }
        
        return clone;
    }
}