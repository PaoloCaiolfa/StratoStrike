package stratostrike.Domain.Model.Shape;
import java.util.ArrayList;
import stratostrike.Domain.Model.Position;

public class Point implements Shape {

        public Point() {}
    
        public Point(int radius) {
           
        }


    @Override
    public ArrayList<Position> getCoveredCordinates(Position pos) {
        ArrayList<Position> coveredPositions = new ArrayList<>();
        coveredPositions.add(pos);
        return coveredPositions;
    }

    @Override
    public void setRadius(int radius) {
        // Point shape doesn't have a radius, so this method can be left empty or throw an exception
    }
    
    @Override
    public int getRadius() {
        return 0; // Point shape doesn't have a radius, so we can return 0
    }

}
