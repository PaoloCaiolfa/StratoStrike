package stratostrike.Domain;

public class Board {
    private final int width;
    private final int length;
    private final int levels;
    private final Position[][][] positions;

    public Board(int width, int length, int levels) {
        this.width = width;
        this.length = length;
        this.levels = levels;
        this.positions = new Position[width][length][levels];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    positions[x][y][z] = new Position(x, y, z);
                }
            }
        }
    }

    public Position getPosition(int x, int y, int z) {
        return positions[x][y][z];
    }

    public int getLength() { return this.length; }
    public int getWidth() { return this.width; }
    public int getLevels() { return this.levels; }
}