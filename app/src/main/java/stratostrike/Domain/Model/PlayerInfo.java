package stratostrike.Domain.Model;
import java.util.ArrayList;
import stratostrike.Domain.Model.Army.StratoShip;   
import stratostrike.Domain.Model.Player;
public record PlayerInfo(Player player, ArrayList<StratoShip> army) {}
    