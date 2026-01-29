package stratostrike.Domain;

import java.util.ArrayList;
import java.util.List;

public class Army {
    protected int capacity;
    private String name;
    protected List<StratoShip> ships;

    public Army() {
        this.ships = new ArrayList<>();
    }

    public void addShip(StratoShip ship) {
        ships.add(ship);
    }

    public void removeShip(StratoShip ship) {
        ships.remove(ship);
    }

    public List<StratoShip> getShips() {
        return ships;
    }

    public StratoShip getShip(StratoShip ship){
        for (StratoShip s : ships) {
            if (s.equals(ship)) {
                return s;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}