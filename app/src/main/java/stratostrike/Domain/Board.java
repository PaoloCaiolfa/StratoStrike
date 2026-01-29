package stratostrike.Domain;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final int width;
    private final int height;
    private final int levels;
    private final Position[][][] positions;

    public Board(int width, int height, int levels) {
        this.width = width;
        this.height = height;
        this.levels = levels;
        this.positions = new Position[width][height][levels];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < levels; z++) {
                    positions[x][y][z] = new Position(x, y, z, null);
                }
            }
        }
    }

    public Position getPosition(int x, int y, int z) {
        return positions[x][y][z];
    }
}