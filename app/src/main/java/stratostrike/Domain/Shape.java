package stratostrike.Domain;
import java.util.ArrayList;

public interface Shape {
    ArrayList<Position> getCoveredCordinates(Position pos);

}