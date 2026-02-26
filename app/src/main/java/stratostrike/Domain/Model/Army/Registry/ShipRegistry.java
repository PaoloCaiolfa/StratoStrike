package stratostrike.Domain.Model.Army.Registry;

import java.util.Map;

import stratostrike.Domain.Model.Army.StratoShip;

public class ShipRegistry {

    private static ShipRegistry instance;

    private Map<String, StratoShip> shipTemplates;

    private ShipRegistry(Map<String, StratoShip> shipTemplates) {
        this.shipTemplates = shipTemplates;
    }

    public static ShipRegistry getInstance() {
        if (instance == null) {
            instance = new ShipRegistry(ShipTemplateLoader.getInstance().loadTemplates());
            System.out.println("ShipRegistry initialized with templates: " + instance.shipTemplates.keySet());
        }
        return instance;
    }

    public StratoShip get(String shipType) {
        StratoShip template = shipTemplates.get(shipType);
        if (template != null) {
            return template.cloneShip();
        }
        throw new IllegalArgumentException("Unknown ship type: " + shipType);
    }
    
}
