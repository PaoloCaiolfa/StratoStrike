package stratostrike.Domain.Model.Army.Factory;

import java.util.ArrayList;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Registry.ShipRegistry;

public class CustomArmyFactory implements ArmyFactory {
    
    private ShipRegistry registry;

    private CustomArmyLoader loader;

    public CustomArmyFactory(ShipRegistry registry, CustomArmyLoader loader) {
        this.registry = ShipRegistry.getInstance();
        this.loader = CustomArmyLoader.getInstance();
    }

    public static CustomArmyFactory getInstance() {
        return new CustomArmyFactory(ShipRegistry.getInstance(), CustomArmyLoader.getInstance());
    }

    /** creazione di armate custom che vengono lette da file json tramite loader che fornisce una lista di nomi di navi 
        * e poi vengono create clonando i prototipi presenti nel registry, in questo modo la logica di creazione è completamente dinamica e dipende solo dal contenuto del file json, senza bisogno di modificare il codice per aggiungere nuove composizioni di armate
        *
    */
    @Override
    public Army createArmy(String armyName) {
       ArrayList<StratoShip> ships = new ArrayList<>();
       ArrayList<String> shipNames = loader.loadTemplates(armyName);

       for (String shipName : shipNames) {
           ships.add(registry.get(shipName));  // REGISTRY richiamato
       }
       return new Army(armyName, ships);
    }

}
