package stratostrike.Domain.Model.Army.Registry;

import java.util.ArrayList;
import java.util.Map;

import stratostrike.Domain.Model.Action.Action;
import stratostrike.Domain.Model.Action.Registry.ActionRegistry;
import stratostrike.Domain.Model.Army.StratoShip;

public class ShipRegistry {

    private static ShipRegistry instance;

    private ActionRegistry actionRegistry;

    private Map<String, StratoShip> shipTemplates;

    private ShipRegistry(Map<String, StratoShip> shipTemplates, ActionRegistry actionRegistry) {
        this.shipTemplates = shipTemplates;
        this.actionRegistry = actionRegistry;
    }

    public static ShipRegistry getInstance() {
        if (instance == null) {
            instance = new ShipRegistry(ShipTemplateLoader.getInstance().loadTemplates(), ActionRegistry.getInstance());
            System.out.println("ShipRegistry initialized with templates: " + instance.shipTemplates.keySet());
        }
        return instance;
    }

    public StratoShip get(String shipType) {
        StratoShip template = shipTemplates.get(shipType);
        if (template != null) {
            StratoShip clonedShip = template.cloneShip();

            ArrayList<Action> resolvedActions = template.getActionsNames().stream()
                    .map(actionRegistry::get)
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);

            clonedShip.setActions(resolvedActions);
            return clonedShip;
        }
        throw new IllegalArgumentException("Unknown ship type: " + shipType);
    }
    
}
