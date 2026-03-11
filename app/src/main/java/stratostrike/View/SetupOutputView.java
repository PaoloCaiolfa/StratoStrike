package stratostrike.View;

import stratostrike.Controller.SetupArmy;
import stratostrike.Settings;

/**
 * Vista responsabile della visualizzazione durante il setup dell'armata.
 * Si occupa di stampare le opzioni e i messaggi relativi alla selezione e composizione dell'armata.
 */
public class SetupOutputView {
    
    /**
     * Visualizza le opzioni per la selezione dell'armata, mostra le armate predefinite, le armate personalizzate salvate e l'opzione per creare una nuova armata personalizzata, chiede al giocatore di inserire l'ID dell'armata da selezionare o l'opzione per creare una nuova armata personalizzata
     * @param setupArmy il controller con i dati da visualizzare
     */
    public void displayArmySelection(SetupArmy setupArmy) {
        System.out.println("\n");
        System.out.println("=== SELEZIONE ARMATA ===");
        System.out.println("Giocatore: " + setupArmy.getPlayerUsername());
        System.out.println("\nScegli la tua armata:");
        
        for (int i = 0; i < Settings.ArmyTipology.size(); i++) {
            System.out.println(i + ": " + Settings.ArmyTipology.get(i));
        }
        
        for (int i = 0; i < setupArmy.getArmyNames().size(); i++) {
            System.out.println((Settings.ArmyTipology.size() + i) + ": " + setupArmy.getArmyNames().get(i));
        }
        
        System.out.println((Settings.ArmyTipology.size() + setupArmy.getArmyNames().size()) + ": Crea armata personalizzata");
    }
    
    /**
     * Visualizza le opzioni per la composizione personalizzata dell'armata.
     * @param setupArmy il controller con i dati da visualizzare
     */
    public void displayArmyComposition(SetupArmy setupArmy) {
        System.out.println("\n");
        System.out.println("=== CREAZIONE ARMATA PERSONALIZZATA ===");

        System.out.println("\nLe tue navi selezionate finora:");
        for (int i = 0; i < setupArmy.getSelectedShipsForComposition().size(); i++) {
            System.out.println((i+1) + ": " + setupArmy.getSelectedShipsForComposition().get(i));
        }
        System.out.println("\nPuoi scegliere ancora " + (Settings.MaxShipsNumberPerPlayer - setupArmy.getSelectedShipsForComposition().size()) + " navi. Attento a non superare il limite di peso della composizione dell'armata!");
        System.out.println("Peso attuale della composizione: " + setupArmy.getCompositionShipWeight() + "/" + Settings.MaxShipsWeightPerPlayer);

        System.out.println("\nSeleziona le navi da aggiungere alla tua armata, una alla volta. Quando hai finito, seleziona l'opzione per terminare la composizione dell'armata.");
        for (int i = 0; i < setupArmy.getAvailableStratoShips().size(); i++) {
            System.out.println(i + ": " + setupArmy.getAvailableStratoShips().get(i));
        }
        System.out.println(setupArmy.getAvailableStratoShips().size() + ": Termina composizione armata");
    }
}
