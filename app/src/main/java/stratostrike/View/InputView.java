
package stratostrike.View;

import java.util.ArrayList;
import java.util.Scanner;

import stratostrike.Settings;

/**
 * Vista di supporto che gestisce gli input da console e le validazioni di correttezza
 */
public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Metodo per ottenere la selezione di una nave da parte del giocatore, mostra la lista delle navi disponibili e chiede al giocatore di inserire l'ID della nave da selezionare, valida l'input e restituisce l'ID selezionato
     * @param size il numero di navi disponibili per la selezione, usato per validare l'input del giocatore
     * @return l'ID della nave selezionata dal giocatore, restituisce un intero compreso tra 0 e size-1
     */
    public static int getShipSelection(int size) {
        int selection = -1;
        int maxIndex = size - 1;
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

    /**
     * Metodo per ottenere la selezione di un'azione da parte del giocatore, mostra la lista delle azioni disponibili e chiede al giocatore di inserire l'ID dell'azione da selezionare, valida l'input e restituisce l'ID selezionato
     * @param size il numero di azioni disponibili per la selezione, usato per validare l'input del giocatore
     * @return l'ID dell'azione selezionata dal giocatore, restituisce un intero compreso tra 0 e size-1
     */
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


    /**
     * Metodo per ottenere la selezione di una posizione bersaglio da parte del giocatore, mostra le coordinate disponibili e chiede al giocatore di inserire le coordinate X, Y e Z del bersaglio, valida l'input e restituisce le coordinate selezionate in una lista di interi
     * @return una lista contenente le coordinate X, Y e Z del bersaglio selezionato
     */
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

    /**
     * Metodo di supporto per leggere un intero da console e validarlo in un range specificato, continua a chiedere l'input finché non viene inserito un numero valido compreso tra min e max
     * @param min
     * @param max
     * @return
     */
    public static int readValidInt(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine(); // Consuma il newline rimasto
                if (val >= min && val <= max) {
                    return val; 
                }
            } else {
                scanner.next();
            }
            System.out.print("Errore! Inserisci un numero tra " + min + " e " + max + ": ");
        }
    }

    /**
     * Metodo per leggere un input da console e validarlo come risposta sì o no, continua a chiedere l'input finché non viene inserito 'y' o 'n', restituisce true se l'input è 'y' e false se l'input è 'n'
     * @return
     */
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

    /**
     * Metodo per leggere il nome dell'armata che vuole attribuire al momento della creazione della sua armata personalizzata
     */
    public static String readArmyName() {
        System.out.print("Inserisci il nome della nuova armata: ");
        String armyName = scanner.nextLine().trim().toUpperCase();
        return armyName;
    }
}
