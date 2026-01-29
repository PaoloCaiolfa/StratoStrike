package stratostrike.Domain;

import java.util.ArrayList;
import java.util.List;

public class Army {
    protected int capacity;
    private String name;
    protected ArrayList<StratoShip> ships;

    public Army() {
        this.ships = new ArrayList<>();
    }

    public void addShip(StratoShip ship) {
        ships.add(ship);
    }

    public void removeShip(StratoShip ship) {
        ships.remove(ship);
    }

    public ArrayList<StratoShip> getShips() {
        return ships;
    }

    public StratoShip get(int index) {
        return ships.get(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}