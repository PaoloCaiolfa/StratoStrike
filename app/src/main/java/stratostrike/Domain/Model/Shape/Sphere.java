package stratostrike.Domain.Model.Shape;
import java.util.ArrayList;

import stratostrike.Domain.Model.Position;
public class Sphere implements Shape{

    
    private int radius;

    public Sphere() {}

    public Sphere(int radius) {
        this.radius = radius;
    }

   @Override
    public ArrayList<Position> getCoveredCordinates(Position pos) {
        ArrayList<Position> coveredPositions = new ArrayList<>();
        int centerX = pos.getX();
        int centerY = pos.getY();
        int centerZ = pos.getZ();
        
        int rSquared = radius * radius; 

        for (int z= centerZ - radius; z <= centerZ; z++) {
                if (z<0) continue;
                int dz = z - centerZ;
                int dzSquared = dz * dz;
            for (int x = centerX - radius; x <= centerX + radius; x++) {
                int dx = x - centerX;
                int dxSquared = dx * dx; 
                for (int y = centerY - radius; y <= centerY + radius; y++) {
                    int dy = y - centerY;
                    int dySquared = dy * dy; 

        
                    if (dxSquared + dySquared + dzSquared <= rSquared) {
                        coveredPositions.add(new Position(x, y, z));
                    }
                }
            }
        }
        return coveredPositions;
    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public void setRadius(int radius) {
        this.radius = radius;
    }

    
}
