
package stratostrike.Input;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import stratostrike.Domain.*;
//import stratostrike.Domain.StratoShip;

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


    public static int getActionSelection(StratoShip ship) {
        int selection = -1;
        int maxIndex = ship.showActions().size() - 1;

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
        System.out.print("\nInserisci la coordinata X del bersaglio (0-" + (board.getWidth()-1) + "): ");
        x = readValidInt(0, board.getWidth() - 1);

        // Coordinata Y
        System.out.print("Inserisci la coordinata Y del bersaglio (0-" + (board.getLength()-1) + "): ");
        y = readValidInt(0, board.getLength() - 1);
        // Coordinata Z
        System.out.println("Livelli disponibili:");
        for (int i = 0; i < board.getLevels(); i++) {
            System.out.println(i + ": " + board.getLevelName(i));
        }
        System.out.print("Inserisci l'indice del livello Z: ");
        z = readValidInt( 0, board.getLevels() - 1);

        coordinates.add(x);
        coordinates.add(y);
        coordinates.add(z);

        return coordinates;
    }

    // Metodo di supporto per evitare di ripetere la logica del controllo
    private static int readValidInt(int min, int max) {
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
    
}