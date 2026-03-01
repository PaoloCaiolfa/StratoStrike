package stratostrike.Domain.Model.Army;

import java.util.ArrayList;

public class Satellite extends SpaceShip {

    public Satellite() {}

    public Satellite(String name, int hp, int weight, int idArmy) {
        this.name = name;
        this.hp = hp;
        this.weight = weight;
        this.idArmy = idArmy;
    }

    @Override
    public StratoShip cloneShip() {
        Satellite clone = new Satellite(
            this.name,
            this.hp,
            this.weight,
            this.idArmy
        );

        clone.setActionsNames(
            this.actionsNames != null 
                ? new ArrayList<>(this.actionsNames) 
                : new ArrayList<>()
        );
        
        return clone;
    }
}
