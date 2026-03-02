package stratostrike.Domain.Model.Army.Factory;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


public class CustomArmyLoader {

    private static CustomArmyLoader instance;

    public static CustomArmyLoader getInstance() {
        if (instance == null) {
            instance = new CustomArmyLoader();
        }
        return instance;
    }

    private CustomArmyLoader() {}


    // prende tutti le chiave per il regsitry (il nome) per creare le navi di un armata 
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


    // prende tutti i nomi delle armate custom definite in armyConfig.json, per mostrarle nella view di selezione armata
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


    public void saveNewArmy(String armyName, List<String> ships) {
            try {
                ObjectMapper mapper = new ObjectMapper();

                // Percorso del file (scrivibile)
                File file = new File("app/src/main/resources/armyConfig.json");

                JsonNode root;
                if (file.exists()) {
                    root = mapper.readTree(file);
                } else {
                    root = mapper.createObjectNode();
                }

                ObjectNode rootObject = (ObjectNode) root;

                ArrayNode armiesNode;
                if (rootObject.has("armies")) {
                    armiesNode = (ArrayNode) rootObject.get("armies");
                } else {
                    armiesNode = mapper.createArrayNode();
                    rootObject.set("armies", armiesNode);
                }

                // Creazione nuova armata
                ObjectNode newArmy = mapper.createObjectNode();
                newArmy.put("name", armyName);

                ArrayNode shipsArray = mapper.createArrayNode();
                for (String ship : ships) {
                    shipsArray.add(ship);
                }

                newArmy.set("ships", shipsArray);

                armiesNode.add(newArmy);

                // Scrittura formattata nel file
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, rootObject);

            } catch (Exception e) {
                throw new RuntimeException("Errore durante il salvataggio della nuova armata", e);
            }
        }


    
    
}
