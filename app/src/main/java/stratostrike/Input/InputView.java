
package stratostrike.Input;

import java.util.Scanner;
import stratostrike.Domain.Army;
import stratostrike.Domain.StratoShip;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Chiede all'utente di selezionare una nave tramite l'indice dell'ArrayList.
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
}