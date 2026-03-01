package stratostrike.Domain.Model.Army;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Action.Action;

@JsonIgnoreProperties(ignoreUnknown = true)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = Fighter.class, name = "Fighter"),
    @JsonSubTypes.Type(value = Satellite.class, name = "Satellite")
})

public abstract class StratoShip {
    public String name;
    public int hp;
    public int weight;
    public ArrayList<String> actionsNames; // For deserialization only

    @JsonIgnore
    public ArrayList<Action> actions; // This will be populated after deserialization using the action names and the ActionRegistry

    public int idArmy;
    
    
    public StratoShip() {
        this.actions = new ArrayList<>();
    }

    public StratoShip(String name, int hp, int weight) {
        this.name = name;
        this.hp = hp;
        this.weight = weight;
        this.actions = new ArrayList<>();
        this.actionsNames = new ArrayList<>();
        this.idArmy = -1;
    }

    public abstract StratoShip cloneShip();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
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

    public void repair(int amount) {
        this.hp = this.hp + amount;
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

    public ArrayList<String> getActionsNames() {
        return actionsNames;
    }

    public void addActionName(String actionName) {
        if (this.actionsNames == null) {
            this.actionsNames = new ArrayList<>();
        }
        this.actionsNames.add(actionName);
    }

    public void setActionsNames(ArrayList<String> actionsNames) {
        this.actionsNames = actionsNames;
    }

    public String toString() {
        return name + " (Peso: " + weight + ", HP: " + hp + ", Azioni: " + actions.size() + ")";
    }
}
