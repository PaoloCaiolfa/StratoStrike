package stratostrike.Domain;

import java.util.ArrayList;

public class Army {
    protected int capacity;
    protected String name;
    protected ArrayList<StratoShip> ships;
    protected int idArmy;

    public Army() {
        this.ships = new ArrayList<>();
    }

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

    public void setShips(ArrayList<StratoShip> ships) {
        this.ships = ships;
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

    public void setIdArmy(int idArmy) {
        this.idArmy = idArmy;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}