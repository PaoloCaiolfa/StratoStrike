package stratostrike.Domain.Model.Army.Registry;

import java.io.InputStream;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomArmyLoader {

    private static CustomArmyLoader instance;

    public static CustomArmyLoader getInstance() {
        if (instance == null) {
            instance = new CustomArmyLoader();
        }
        return instance;
    }

    private CustomArmyLoader() {}


    public ArrayList<String> loadTemplates(String armyName) {

        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("armyConfig.json")) {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(is);
            JsonNode armiesNode = root.get("armies");

            // Deserializziamo la lista di navi
            ArrayList<String> armyNames = new ArrayList<>();

            for (JsonNode armyNode : armiesNode) {
                if (armyNode.get("name").asText().equals(armyName)) {
                    JsonNode shipsNode = armyNode.get("ships");
                    for (JsonNode shipNode : shipsNode) {
                        armyNames.add(shipNode.asText());
                    }
                }
            }

            return armyNames;

        } catch (Exception e) {
            throw new RuntimeException("Errore durante il caricamento dei template delle navi", e);
        }
    }

    public ArrayList<String> getArmyName() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("armyConfig.json")) {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(is);
            JsonNode armiesNode = root.get("armies");

            // Deserializziamo la lista di navi
            ArrayList<String> armyNames = new ArrayList<>();

            for (JsonNode armyNode : armiesNode) {
                armyNames.add(armyNode.get("name").asText());
            }

            return armyNames;

        } catch (Exception e) {
            throw new RuntimeException("Errore durante il caricamento dei template delle navi", e);
        }
    }
    
}
