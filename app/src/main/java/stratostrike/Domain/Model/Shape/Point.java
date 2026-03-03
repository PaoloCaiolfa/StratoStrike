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
    
}
