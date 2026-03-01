package stratostrike;
import java.util.List;
public class Settings {

    // Settings for the Stratostrike game: board dimensions
    public static final int BoardLengthStandard = 20;
    public static final int BoardWidthStandard = 20;
    public static final List<String> BoardLevelsStandard = List.of(new String[] {
        "CIELO",
        "SPAZIO"

    });

    // Selection of the types of armies available in the game
    public static final List<String> ArmyTipology = List.of(new String[] {
        "HUMAN",
        "CYBER",
    });
    public static final int MaxShipsWeightPerPlayer = 20;
    public static final int MaxShipsNumberPerPlayer = 8;

    // Settings for the color of the ships on the board
    public static final String OPPONENT = "\u001B[31m"; // Rosso
    public static final String MINE = "\u001B[34m";     // Blu
    public static final String RESET = "\u001B[0m";    // Bianco
}