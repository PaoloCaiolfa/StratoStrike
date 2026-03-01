package stratostrike.Domain.Model.Army.Factory;

import java.util.ArrayList;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Registry.ShipRegistry;

public class ArmyManager {

    public static ArmyFactory getFactory(String fazione) {
        try {
            String fazioneNormalizzata = fazione.substring(0, 1).toUpperCase() + fazione.substring(1).toLowerCase();
            String className = "stratostrike.Domain.Model.Army.Factory." + fazioneNormalizzata + "ArmyFactory";
            
            Class<?> clazz = Class.forName(className);

            java.lang.reflect.Method method = clazz.getMethod("getInstance");

            return (ArmyFactory) method.invoke(null);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> getAvailableShipsToString() {
        ArrayList<StratoShip> ships = ShipRegistry.getInstance().getAllShips();
        ArrayList<String> shipNames = new ArrayList<>();
        for (StratoShip ship : ships) {
            shipNames.add(ship.toString());
        }
        return shipNames;
    }

    public static ArrayList<String> getAvailableShipsNames() {
        ArrayList<StratoShip> ships = ShipRegistry.getInstance().getAllShips();
        ArrayList<String> shipNames = new ArrayList<>();
        for (StratoShip ship : ships) {
            shipNames.add(ship.getName());
        }
        return shipNames;
    }

    public static int calculateCompositionWeight(ArrayList<String> composition) {
        int totalWeight = 0;
        for (String shipName : composition) {
            StratoShip ship = ShipRegistry.getInstance().get(shipName);
            if (ship != null) {
                totalWeight += ship.getWeight();
            }
        }
        return totalWeight;
    }
}
       

