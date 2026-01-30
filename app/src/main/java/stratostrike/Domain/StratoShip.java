package stratostrike.Domain;

import java.util.ArrayList;

public abstract class StratoShip {
    private String name;
    private int hp;
    protected ArrayList<Action> actions;
    

    public StratoShip(String name, int hp) {
        this.name = name;
        this.hp = hp;
        this.actions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return hp;
    }

    public void setHealth(int hp) {
        this.hp = hp;
    }

    public void changeStatus(int damage) {
        if (damage > 0 && damage <= this.hp)
            this.hp = (this.hp - damage);
        else if (damage > this.hp)
            this.hp = 0;
    }

    public ArrayList<Action> showActions() {
        return actions;
    }

    public void addAction(Action action) {
        actions.add(action);
    }


}
