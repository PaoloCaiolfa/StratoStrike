
package stratostrike.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import stratostrike.Settings;
import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Factory.ArmyFactory;
import stratostrike.Domain.Model.Army.Factory.ArmyManager;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static int getShipSelection(int maxIndex) {
        int selection = -1;

        while (selection < 0 || selection > maxIndex) {
            System.out.print("\nInserisci l'ID della nave da selezionare (0-" + maxIndex + "): ");

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();

                if (selection < 0 || selection > maxIndex) {
                    System.out.println("Errore: ID non valido. Riprova.");
                }
            } else {
                System.out.println("Errore: Inserisci un numero intero.");
                scanner.next();
            }
        }
        return selection;
    }

    public static int getActionSelection(int size) {
        int selection = -1;
        int maxIndex = size - 1;

        while (selection < 0 || selection > maxIndex) {
            System.out.print("\nInserisci l'ID dell'azione da selezionare (0-" + maxIndex + "): ");

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();

                if (selection < 0 || selection > maxIndex) {
                    System.out.println("Errore: ID non valido. Riprova.");
                }
            } else {
                System.out.println("Errore: Inserisci un numero intero.");
                scanner.next(); 
            }
        }
        return selection;
    }

    public static ArrayList<Integer> getPositionTarget() {
        ArrayList<Integer> coordinates = new ArrayList<>();
        int x = -1, y = -1, z = -1;

        int length = Settings.BoardLengthStandard;
        int width = Settings.BoardWidthStandard;
        System.out.print("\nInserisci la coordinata X del bersaglio (0-" + (width- 1) + "): ");
        x = readValidInt(0, width - 1);

        System.out.print("Inserisci la coordinata Y del bersaglio (0-" + (length - 1) + "): ");
        y = readValidInt(0, length - 1);

        System.out.println("Livelli disponibili:");
        for (int i = 0; i < Settings.BoardLevelsStandard.size(); i++) {
            System.out.println(i + ": " + Settings.BoardLevelsStandard.get(i));
        }
        System.out.print("Inserisci l'indice del livello Z: ");
        z = readValidInt(0, Settings.BoardLevelsStandard.size() - 1);
        coordinates.add(x);
        coordinates.add(y);
        coordinates.add(z);

        return coordinates;
    }

    // Metodo di supporto per evitare di ripetere la logica del controllo
    public static int readValidInt(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                if (val >= min && val <= max) {
                    return val; 
                }
            } else {
                scanner.next();
            }
            System.out.print("Errore! Inserisci un numero tra " + min + " e " + max + ": ");
        }
    }

    // CODICE PER TESTARE LE FACTORY

    // Metodo per scegliere l'armata
    public ArmyFactory scegliArmata() {
        System.out.println("=== SELEZIONE ARMATA ===");
        System.out.println("Scegli la fazione desiderata:");
        for (String el : Settings.ArmyTipology) {
            System.out.println((Settings.ArmyTipology.indexOf(el)) + ": " + el);
        }

        int scelta = scanner.nextInt();
        scanner.nextLine(); 
        if (scelta >= 0 && scelta < Settings.ArmyTipology.size()) {
            String fazione = Settings.ArmyTipology.get(scelta);
            System.out.println("Hai scelto: " + fazione);
            return ArmyManager.getFactory(fazione);
            
        } else {
            System.out.println("Scelta non valida. Riprova.");
            return scegliArmata(); 
        }
    }

    // Metodo per visualizzare i veicoli dell'armata creata
    public void stampaStatoArmata(Army armata) {
        System.out.println("\n--- COMPOSIZIONE ARMATA " + armata.getName() + " ---");
        for (int i = 0; i < armata.getShips().size(); i++) {
            StratoShip nave = armata.getShips().get(i);
            System.out.println("ID " + i + " -> " + nave.getName() + " [" + nave.getClass().getSimpleName() + "]");
            System.out.println("   HP: " + nave.getHp());
            System.out.println("   Azioni: " + nave.getActions().size() + " disponibili.");
            System.out.println("---------------------------");
        }
    }

    public static boolean readYesOrNo() {
        while (true) {
        if (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim().toLowerCase();
            
            // Gestiamo il caso di riga vuota (solo invio)
            if (input.isEmpty()) continue; 

            if (input.equals("y") || input.equals("n")) {
                return input.equals("y");
            }
        }
        
        // Se arriviamo qui, l'input era sbagliato o mancante
        System.out.println("Input non valido. Inserisci 'y' per SI o 'n' per NO:");
        
        // Se lo scanner non ha più input (es. file terminato), dobbiamo uscire
        if (!scanner.hasNext()) {
            throw new IllegalStateException("Scanner terminato o input non disponibile");
        }
    }
    }
}
