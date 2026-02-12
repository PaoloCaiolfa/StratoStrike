package stratostrike.Domain.Model.Army.Factory;

import java.io.InputStream;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;

public class HumanArmyFactory implements ArmyFactory{
    
    private static HumanArmyFactory instance;
    private ArrayList<StratoShip> prototipi = new ArrayList<>(); 

    private HumanArmyFactory() {
        uploadConfiguration();
    }

    public static HumanArmyFactory getInstance() {
        if (instance == null) instance = new HumanArmyFactory();
        return instance;
    }

    private void uploadConfiguration() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getClassLoader().getResourceAsStream("armies.json");
            JsonNode root = mapper.readTree(is);
            JsonNode human = root.get("HUMAN").get(0);

            ArrayList<StratoShip> cielo = mapper.readerForListOf(StratoShip.class).readValue(human.get("cielo"));
            ArrayList<StratoShip> spazio = mapper.readerForListOf(StratoShip.class).readValue(human.get("spazio"));

            this.prototipi.clear();
            prototipi.addAll(cielo);
            prototipi.addAll(spazio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Army createArmy() {
        Army humanArmy = new Army("HUMAN",0);
        for (StratoShip p : prototipi) {
            humanArmy.addShip(p.cloneShip());
        }
        return humanArmy;
    }

    public ArrayList<StratoShip> getPrototipi() {
        return prototipi;
    }

    public void setPrototipi(ArrayList<StratoShip> prototipi) {
        this.prototipi = prototipi;
    }
}
