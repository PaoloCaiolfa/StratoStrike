package stratostrike.Domain;
import java.util.List;
public class Board {
    private final int width;
    private final int length;
    
    private final int levels;
    private final List<String> levelNames; // Dinamico!
    private final Position[][][] positions;

    public Board(int width, int length, List<String> names) {

        this.width = width;
        this.length = length;
        this.levelNames = names;
        this.levels = names.size(); 
        this.positions = new Position[width][length][levels];
               for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    positions[x][y][z] = new Position(x, y, z);
                }
            }
        }
    }

    public String getLevelName(int z) {
        return levelNames.get(z);
    }


    public Position getPosition(int x, int y, int z) {
        return positions[x][y][z];
    }

    public int getLength() { return this.length; }
    public int getWidth() { return this.width; }
    public int getLevels() { return this.levels; }


    public void placeShip(StratoShip ship,int x, int y, int z) {
        positions[x][y][z].setShip(ship);
    }

    public Position getShipPosition(StratoShip ship) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    if (positions[x][y][z].getShip() == ship) {
                        return positions[x][y][z];
                    }
                }
            }
        }
        return null;
    }

    
}