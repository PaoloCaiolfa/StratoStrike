package stratostrike.Domain.Model;

import java.util.ArrayList;

public class Cross implements Shape {
    
    private int length;

    public Cross() {}

    public Cross(int length) {
        this.length = length;
    }

    // cross shape attack

    @Override
    public ArrayList<Position> getCoveredCordinates(Position pos) {
        ArrayList<Position> coveredPositions = new ArrayList<>();
        int startX = pos.getX();
        int startY = pos.getY();
        int startZ = pos.getZ();

        for (int i = 0; i < length; i++) {
            coveredPositions.add(new Position(startX + i, startY, startZ));
            coveredPositions.add(new Position(startX - i, startY, startZ));
            coveredPositions.add(new Position(startX, startY + i, startZ));
            coveredPositions.add(new Position(startX, startY - i, startZ));
        }
        return coveredPositions;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
}
