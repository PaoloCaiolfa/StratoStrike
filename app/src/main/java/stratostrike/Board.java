package stratostrike;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int width;
    private final int height;
    private final List<StratoShip> ships;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        this.ships = new ArrayList<>();
    }

    public void placeShip(StratoShip ship, Position position) {
        // Logic to place the ship on the board at the specified position
    }

    public boolean checkPosition(Position position) {
        // Logic to check if the position is valid and available
        return true; // Placeholder return value
    }

    public List<StratoShip> getShips() {
        return ships;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}