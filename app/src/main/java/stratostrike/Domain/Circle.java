package stratostrike.Domain;
import java.util.ArrayList;
public class Circle implements Shape{
    
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

   @Override
public ArrayList<Position> getCoveredCordinates(Position pos) {
    ArrayList<Position> coveredPositions = new ArrayList<>();
    int centerX = pos.getX();
    int centerY = pos.getY();
    int centerZ = pos.getZ();
    
    int rSquared = radius * radius; 

    for (int x = centerX - radius; x <= centerX + radius; x++) {
        int dx = x - centerX;
        int dxSquared = dx * dx; 
        for (int y = centerY - radius; y <= centerY + radius; y++) {
            int dy = y - centerY;
            int dySquared = dy * dy; 

  
            if (dxSquared + dySquared <= rSquared) {
                coveredPositions.add(new Position(x, y, centerZ));
            }
        }
    }
    return coveredPositions;
}




}