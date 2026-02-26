package stratostrike.Domain.Model.Army.Factory;

import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Registry.ShipRegistry;

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

    @Override
    public Army createArmy(String armyName) {
        prototipi.clear();
        prototipi.add(shipRegistry.get("Fighter"));
        prototipi.add(shipRegistry.get("Satellite"));
        prototipi.add(shipRegistry.get("Satellite"));
        // qui non ci vanno prototipi hardcoded, ma metteremo dentro in base ad una logica specifica


        Army humanArmy = new Army(armyName,0);
        for (StratoShip p : prototipi) {
            humanArmy.addShip(p.cloneShip());
        }
        return humanArmy;
    }

}
