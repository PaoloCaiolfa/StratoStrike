package stratostrike.Domain.Model.Action.Registry;

import stratostrike.Domain.Model.Action.Action;

import java.util.Map;

// Singleton che carica i template delle azioni all'avvio del gioco e fornisce un metodo per ottenere una nuova 
// istanza di un'azione dato il suo nome, utilizzando il pattern prototype per clonare i template e restituire nuove istanze ogni volta che vengono richieste

public class ActionRegistry {

    private static ActionRegistry instance;

    private Map<String, Action> actionTemplates;

    private ActionRegistry(Map<String, Action> actionTemplates) {
        this.actionTemplates = actionTemplates;
    }

    public static ActionRegistry getInstance() {
        if (instance == null) {
            instance = new ActionRegistry(ActionTemplateLoader.getInstance().loadTemplates());
            System.out.println("ActionRegistry initialized with templates: " + instance.actionTemplates.keySet());
        }
        return instance;
    }

    public Action get(String actionName) {
        Action template = actionTemplates.get(actionName);
        if (template != null) {
            return template.cloneAction();
        }
        throw new IllegalArgumentException("Unknown action name: " + actionName);
    }
    
}
