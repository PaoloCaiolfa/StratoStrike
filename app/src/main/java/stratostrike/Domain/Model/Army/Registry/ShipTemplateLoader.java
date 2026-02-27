package stratostrike.Domain.Model.Army.Registry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import stratostrike.Domain.Model.Army.StratoShip;

public class ShipTemplateLoader {
    
    private static ShipTemplateLoader instance;

    public static ShipTemplateLoader getInstance() {
        if (instance == null) {
            instance = new ShipTemplateLoader();
        }
        return instance;
    }

    private ShipTemplateLoader() {
    }


    public Map<String, StratoShip> loadTemplates() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("shipsConfig.json")) {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(is);
            JsonNode shipsNode = root.get("ships");

            // Deserializziamo la lista di navi
            ArrayList<StratoShip> ships =
                    mapper.readerForListOf(StratoShip.class)
                          .readValue(shipsNode);

            // Convertiamo la lista in una Map<id, template>
            return ships.stream()
                    .collect(java.util.stream.Collectors.toMap(
                            StratoShip::getName,
                            ship -> ship
                    ));

        } catch (Exception e) {
            throw new RuntimeException("Errore durante il caricamento dei template delle navi", e);
        }
    }


}
