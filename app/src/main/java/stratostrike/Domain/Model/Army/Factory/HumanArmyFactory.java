package stratostrike.Domain.Model.Army.Factory;

import java.util.ArrayList;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Registry.ShipRegistry;

public class HumanArmyFactory implements ArmyFactory{
    
    private static HumanArmyFactory instance;

    private ShipRegistry shipRegistry;

    private ArrayList<StratoShip> prototipi = new ArrayList<>(); 

    private HumanArmyFactory() {
        shipRegistry= ShipRegistry.getInstance();
    }

    public static HumanArmyFactory getInstance() {
        if (instance == null) instance = new HumanArmyFactory();
        return instance;
    }

    @Override
    public Army createArmy(String armyName) {
        prototipi.clear();
        prototipi.add(shipRegistry.get("F-35"));
        prototipi.add(shipRegistry.get("F-35"));
        // qui non ci vanno prototipi hardcoded, ma metteremo dentro in base ad una logica specifica


        Army humanArmy = new Army(armyName,0);
        for (StratoShip p : prototipi) {
            humanArmy.addShip(p.cloneShip());
        }
        return humanArmy;
    }
}
