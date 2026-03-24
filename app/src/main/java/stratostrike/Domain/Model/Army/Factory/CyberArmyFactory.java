package stratostrike.Domain.Model.Army.Factory;

import java.util.ArrayList;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.Registry.ShipRegistry;
import stratostrike.Domain.Model.Army.StratoShip;

public class CyberArmyFactory implements ArmyFactory {
    
    private static CyberArmyFactory instance;

    private ShipRegistry shipRegistry;
    private ArrayList<StratoShip> prototipi = new ArrayList<>(); 

    private CyberArmyFactory() {
        shipRegistry= ShipRegistry.getInstance();
    }

    public static CyberArmyFactory getInstance() {
        if (instance == null) instance = new CyberArmyFactory();
        return instance;
    }


     /** 
      * differente implementazione della logica di creazione, in questo caso è un set prefissato di navi 
     */
    @Override
    public Army createArmy(String armyName) {
        prototipi.clear();
        prototipi.add(shipRegistry.get("F-35"));
        prototipi.add(shipRegistry.get("F-35"));
        prototipi.add(shipRegistry.get("SAT-22"));
        // qui non ci vanno prototipi hardcoded, ma metteremo dentro in base ad una logica specifica


        Army cyberArmy = new Army(armyName,0);
        for (StratoShip p : prototipi) {
            cyberArmy.addShip(p);
        }
        return cyberArmy;
    }

}
