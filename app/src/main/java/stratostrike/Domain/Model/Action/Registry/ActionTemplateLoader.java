package stratostrike.Domain.Model.Action.Registry;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import stratostrike.Domain.Model.Action.Action;

public class ActionTemplateLoader {

    private static ActionTemplateLoader instance;

    public static ActionTemplateLoader getInstance() {
        if (instance == null) {
            instance = new ActionTemplateLoader();
        }
        return instance;
    }

    private ActionTemplateLoader() {}

    public Map<String, Action> loadTemplates() {
        try (InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("actionsConfig.json")) {

            ObjectMapper mapper = new ObjectMapper();

            JsonNode root = mapper.readTree(is);
            JsonNode actionNode = root.get("actions");

            // Deserializziamo la lista di navi
            ArrayList<Action> actions =
                    mapper.readerForListOf(Action.class)
                          .readValue(actionNode);

            // Convertiamo la lista in una Map<id, template>
            return actions.stream()
                    .collect(java.util.stream.Collectors.toMap(
                            Action::getName,
                            action -> action
                    ));

        } catch (Exception e) {
            throw new RuntimeException("Errore durante il caricamento dei template delle azioni", e);
        }
    }
}
