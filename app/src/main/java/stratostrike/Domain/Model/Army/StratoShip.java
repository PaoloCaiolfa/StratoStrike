package stratostrike.Domain.Model.Army;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Action.Action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Fighter.class, name = "Fighter"),
    @JsonSubTypes.Type(value = Satellite.class, name = "Satellite")
})

public abstract class StratoShip {
    public String name;
    public int hp;
    public ArrayList<Action> actions;
    public int idArmy;
    
    public StratoShip() {
        this.actions = new ArrayList<>();
    }

    public StratoShip(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.actions = new ArrayList<>();
        this.idArmy = -1;
    }

    public abstract StratoShip cloneShip();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDestroyed() {
        return this.hp <= 0;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void changeStatus(int damage) {
        if (damage > 0 && damage <= this.hp)
            this.hp = (this.hp - damage);
        else if (damage > this.hp)
            this.hp = 0;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }

    public void setIdArmy(int idArmy) {
        this.idArmy = idArmy;
    }
    public int getIdArmy() {
        return idArmy;
    }
}
