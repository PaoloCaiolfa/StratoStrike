package stratostrike.Domain.Model.Army.Factory;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Registry.CustomArmyLoader;
import stratostrike.Domain.Model.Army.Registry.ShipRegistry;

public class CustomArmyFactory implements ArmyFactory {
    
    private ShipRegistry registry;

    private CustomArmyLoader loader;

    public CustomArmyFactory(ShipRegistry registry, CustomArmyLoader loader) {
        this.registry = ShipRegistry.getInstance();
        this.loader = CustomArmyLoader.getInstance();
    }

    public static CustomArmyFactory getInstance() {
        return new CustomArmyFactory(ShipRegistry.getInstance(), CustomArmyLoader.getInstance());
    }

    @Override
    public Army createArmy(String armyName) {
       ArrayList<StratoShip> ships = new ArrayList<>();
       ArrayList<String> shipNames = loader.loadTemplates(armyName);

       for (String shipName : shipNames) {
           ships.add(registry.get(shipName));
       }
       return new Army(armyName, ships);
    }

    public List<StratoShip> cloneShips(List<Class<? extends StratoShip>> shipClasses) {
        List<StratoShip> cloned = new ArrayList<>();
        for (Class<? extends StratoShip> cls : shipClasses) {
            try {
                cloned.add(cls.getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cloned;
    }
}
