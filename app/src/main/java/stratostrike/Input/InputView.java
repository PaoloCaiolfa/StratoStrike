
package stratostrike.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import stratostrike.Settings;
import stratostrike.Controller.ArmyManager;
import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Army.Factory.ArmyFactory;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Chiede all'utente di selezionare una nave tramite l'indice dell'ArrayList.
     * 
     * @param army L'armata del giocatore corrente.
     * @return L'indice (ID) valido della nave selezionata.
     */
    public static int getShipSelection(Army army) {
        int selection = -1;
        int maxIndex = army.getShips().size() - 1;

        while (selection < 0 || selection > maxIndex) {
            System.out.print("\nInserisci l'ID della nave da selezionare (0-" + maxIndex + "): ");

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();

                if (selection < 0 || selection > maxIndex) {
                    System.out.println("Errore: ID non valido. Riprova.");
                }
            } else {
                System.out.println("Errore: Inserisci un numero intero.");
                scanner.next(); // Pulisce il buffer dello scanner
            }
        }
        return selection;
    }

    public static int getActionSelection(StratoShip ship) {
        int selection = -1;
        int maxIndex = ship.getActions().size() - 1;

        while (selection < 0 || selection > maxIndex) {
            System.out.print("\nInserisci l'ID dell'azione da selezionare (0-" + maxIndex + "): ");

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();

                if (selection < 0 || selection > maxIndex) {
                    System.out.println("Errore: ID non valido. Riprova.");
                }
            } else {
                System.out.println("Errore: Inserisci un numero intero.");
                scanner.next(); // Pulisce il buffer dello scanner
            }
        }
        return selection;
    }

    public static List<Integer> getPositionTarget(Board board) {
        List<Integer> coordinates = new ArrayList<>();
        int x = -1, y = -1, z = -1;

        // Coordinata X
        System.out.print("\nInserisci la coordinata X del bersaglio (0-" + (board.getWidth() - 1) + "): ");
        x = readValidInt(0, board.getWidth() - 1);

        // Coordinata Y
        System.out.print("Inserisci la coordinata Y del bersaglio (0-" + (board.getLength() - 1) + "): ");
        y = readValidInt(0, board.getLength() - 1);
        // Coordinata Z
        System.out.println("Livelli disponibili:");
        for (int i = 0; i < board.getLevels(); i++) {
            System.out.println(i + ": " + board.getLevelName(i));
        }
        System.out.print("Inserisci l'indice del livello Z: ");
        z = readValidInt(0, board.getLevels() - 1);

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
                    return val; // Input valido, usciamo dal ciclo
                }
            } else {
                scanner.next(); // Pulisce l'input non numerico
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
        scanner.nextLine(); // Pulisce il buffer
        if (scelta >= 0 && scelta < Settings.ArmyTipology.size()) {
            String fazione = Settings.ArmyTipology.get(scelta);
            System.out.println("Hai scelto: " + fazione);
            return ArmyManager.getFactory(fazione);
            
        } else {
            System.out.println("Scelta non valida. Riprova.");
            return scegliArmata(); // Richiama ricorsivamente in caso di scelta errata
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
}
