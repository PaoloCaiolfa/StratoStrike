package stratostrike;
import java.awt.Color;
import java.util.List;
public class Settings {
    public static final String VERSION = "0.1.0";
    public static final boolean USE_GUI = true;

    // Settings for the Stratostrike game: board dimensions
    public static final int BoardLengthStandard = 15;
    public static final int BoardWidthStandard = 15;
    public static final int SPECIAL_ABILITY_ACTIVATION = 3;
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

    public static final Color PRIMARY = new Color(13, 27, 42);
    public static final Color SECONDARY = new Color(90, 104, 114);
    public static final Color TERTIARY = new Color(157, 126, 255);
    public static final Color CONTRAST = new Color(0, 255, 198);

    public static final Color[] TEAM_COLORS = {
        new Color(80, 140, 255),   // player 0
        new Color(255, 102, 102),    // player 1
    };


    public static final String MAIN_FONT = "Comic Sans MS";
}