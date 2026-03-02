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
    // auto inizializzazione del registry, con caricamento dei template e risoluzione delle azioni
    public static ShipRegistry getInstance() {
        if (instance == null) {
            instance = new ShipRegistry(ShipTemplateLoader.getInstance().loadTemplates(), ActionRegistry.getInstance()); 
            System.out.println("ShipRegistry initialized with templates: " + instance.shipTemplates.keySet());
        }
        return instance;
    }

    public StratoShip get(String shipType) {
        StratoShip template = shipTemplates.get(shipType);  // a partire dal nome prende il modello di nave corrispondente
        if (template != null) {
            StratoShip clonedShip = template.cloneShip(); // clona la nave per evitare modifiche al template originale

            ArrayList<Action> resolvedActions = template.getActionsNames().stream() // per ogni nome di azione associato alla nave, recupera l'azione corrispondente dal registry delle azioni. 
                    .map(actionRegistry::get) 
                    .collect(ArrayList::new, ArrayList::add, ArrayList::addAll); // stream in struttura dati 

            clonedShip.setActions(resolvedActions);
            return clonedShip;
        }
        throw new IllegalArgumentException("Unknown ship type: " + shipType);
    }

    public ArrayList<StratoShip> getAllShips() {
        ArrayList<StratoShip> ships = new ArrayList<>();
        for (String shipType : shipTemplates.keySet()) {
            ships.add(get(shipType));
        }
        return ships;
    }
    
}
