package stratostrike.Domain;

import java.util.ArrayList;

public class Army {
    protected int capacity;
    private String name;
    protected ArrayList<StratoShip> ships;
    protected int idArmy;

    public Army(int idArmy) {
        this.ships = new ArrayList<>();
        this.idArmy = idArmy;
    }

    public void addShip(StratoShip ship) {
        ships.add(ship);
        ship.setIdArmy(this.idArmy);
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

    public int getIdArmy() {
        return idArmy;
    }
}