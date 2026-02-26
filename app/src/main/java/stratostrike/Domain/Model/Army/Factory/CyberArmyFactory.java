package stratostrike.Domain.Model.Army.Factory;

import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;

public class CyberArmyFactory implements ArmyFactory {
    
    private static CyberArmyFactory instance;
    private ArrayList<StratoShip> prototipi = new ArrayList<>(); 

    private CyberArmyFactory() {
        uploadConfiguration();
    }

    public static CyberArmyFactory getInstance() {
        if (instance == null) instance = new CyberArmyFactory();
        return instance;
    }

    private void uploadConfiguration() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("armies.json");
            JsonNode root = mapper.readTree(is);
            JsonNode cyber = root.get("CYBER");

            // Deserializziamo i veicoli cielo (SkyShip) e spazio (SpaceShip)
            // Jackson userà i tag @JsonSubTypes che ci sono in StratoShip
            if (cyber != null && cyber.isArray()) {
            JsonNode firstElement = cyber.get(0);

            ArrayList<StratoShip> cielo = mapper.readerForListOf(StratoShip.class)
                                                .readValue(firstElement.get("cielo"));
            ArrayList<StratoShip> spazio = mapper.readerForListOf(StratoShip.class)
                                                 .readValue(firstElement.get("spazio"));

            this.prototipi.clear();
            this.prototipi.addAll(cielo);
            this.prototipi.addAll(spazio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Army createArmy(String armyName) {
        Army cyberArmy = new Army("CYBER",1);
        for (StratoShip p : prototipi) {
            cyberArmy.addShip(p.cloneShip());
        }
        return cyberArmy;
    }

    public ArrayList<StratoShip> getPrototipi() {
        return prototipi;
    }

    public void setPrototipi(ArrayList<StratoShip> prototipi) {
        this.prototipi = prototipi;
    }


}
