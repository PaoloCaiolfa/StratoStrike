package stratostrike.Domain.Model.Army.Factory;

import java.io.File;
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

    // Metodo helper per ottenere il percorso del file sorgente
    private File getSourceConfigFile() {
        // Trova il file compilato usando il classloader
        java.net.URL resourceUrl = getClass().getClassLoader().getResource("armyConfig.json");
        if (resourceUrl != null) {
            // Converti il percorso compilato in percorso sorgente
            String resourcePath = resourceUrl.getPath();
            String sourceFilePath = resourcePath.replace("/build/resources/main/", "/src/main/resources/");
            return new File(sourceFilePath);
        }
        // Fallback: usa il percorso relativo
        return new File(System.getProperty("user.dir") + "/app/src/main/resources/armyConfig.json");
    }


    // prende tutti le chiave per il regsitry (il nome) per creare le navi di un armata 
    public ArrayList<String> loadTemplates(String armyName) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            File configFile = getSourceConfigFile();
            
            JsonNode root = mapper.readTree(configFile);
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
        try {
            ObjectMapper mapper = new ObjectMapper();
            File configFile = getSourceConfigFile();
            
            JsonNode root = mapper.readTree(configFile);
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
                File configFile = getSourceConfigFile();
                
                JsonNode root;
                if (configFile.exists()) {
                    root = mapper.readTree(configFile);
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

                // Scrittura formattata nel file sorgente
                mapper.writerWithDefaultPrettyPrinter().writeValue(configFile, rootObject);

            } catch (Exception e) {
                throw new RuntimeException("Errore durante il salvataggio della nuova armata", e);
            }
        }


    
    
}
