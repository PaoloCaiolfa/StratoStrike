package stratostrike.Domain.Model;
import java.util.ArrayList;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Position;
public record AffectedPositionsAndArmy(ArrayList<Position> affectedPositions, ArrayList<StratoShip> army) {}