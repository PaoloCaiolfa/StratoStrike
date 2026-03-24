package stratostrike.Domain.Model;
import java.util.ArrayList;
import java.util.List;

import stratostrike.Domain.Model.Army.Army;
import stratostrike.Domain.Model.Army.SkyShip;
import stratostrike.Domain.Model.Army.SpaceShip;
import stratostrike.Domain.Model.Army.StratoShip;


/**
 * Classe che rappresenta la board di gioco, 
 */
public class Board {
    private final int width;
    private final int length;
    private final int levels;
    private final List<String> levelNames; // Dinamico!
    private final Position[][][] positions;

    public Board(int width, int length, List<String> names) {

        this.width = width;
        this.length = length;
        this.levelNames = names;
        this.levels = names.size(); 
        this.positions = new Position[width][length][levels];
               for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    positions[x][y][z] = new Position(x, y, z);
                }
            }
        }
    }

    /* =============== GETTERS =============== */

    /**
     * Metodo per ottenere il nome del livello in base al suo indice, usato principalmente per la visualizzazione del tabellone di gioco, restituisce una stringa che rappresenta il nome del livello corrispondente all'indice z
     * @param z
     * @return
     */
    public String getLevelName(int z) {
        return levelNames.get(z);
    }


    /**
     * Metodo per ottenere la posizione del tabellone in base alle coordinate x, y e z, restituisce un oggetto Position che rappresenta la posizione corrispondente alle coordinate fornite
     * @param x
     * @param y
     * @param z
     * @return
     */
    public Position getPosition(int x, int y, int z) {
        return positions[x][y][z];
    }

    /**
     * Metodo per ottenere la posizione del tabellone in base a una lista di coordinate, restituisce un oggetto Position che rappresenta la posizione corrispondente alle coordinate fornite nella lista, dove la lista contiene le coordinate x, y e z in ordine
     * @param coordinates
     * @return
     */
    public Position getPositionByCoordinates(ArrayList<Integer> coordinates) {
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        int z = coordinates.get(2);
        return getPosition(x, y, z);
    }

    public int getLength() { return this.length; }
    public int getWidth() { return this.width; }
    public int getLevels() { return this.levels; }


    /* ================ SHIP METHODS =============== */

    /**
     * Metodo per posizionare una nave sulla board, utilizzato principalmente per il setup iniziale del gioco
     * @param ship
     * @param x
     * @param y
     * @param z
     */
    public void placeShip(StratoShip ship,int x, int y, int z) {
        positions[x][y][z].setShip(ship);
    }


    /**
     * Metodo per muovere una nave sulla board
     * @param ship
     * @param newPosition
     */
    public void moveShip(StratoShip ship, Position newPosition) {
        Position currentPosition = getShipPosition(ship);
        if (currentPosition != null) {
            currentPosition.setShip(null); 
        }
        newPosition.setShip(ship); 
    }

    /**
     * Metodo per posizionare casualmente le navi di un'armata sulla board, utilizzato principalmente per il setup iniziale del gioco, posiziona le navi in modo strategico in base al loro tipo, ad esempio posizionando le navi spaziali al livello 1 e le navi terrestri al livello 0, mentre per altre navi il posizionamento è casuale su qualsiasi livello
     * @param army
     */
    public void setupRandomArmyPlacement(Army army) {
        for (StratoShip ship : army.getShips()) {
            int x, y, z;

            // Posizionamento strategico in base al tipo di nave
            if(ship instanceof SpaceShip) {
                z = 1; // Posizioniamo le navi spaziali al livello 1
            } else if (ship instanceof SkyShip) {
                z = 0; // Posizioniamo le navi terrestri al livello 0
            } else { 
                z = (int) (Math.random() * levels); // Per altre navi, posizionamento casuale su qualsiasi livello
            }

            do {
                x = (int) (Math.random() * width);
                y = (int) (Math.random() * length);
            } while (positions[x][y][z].getShip() != null); // Assicuriamoci che la posizione non sia già occupata
            
            placeShip(ship, x, y, z);
        }
    }

    /**
     * Metodo per verificare se una nave è presente sulla board
     * @param ship
     * @return true se la nave è presente sulla board, false altrimenti
     */
    public boolean containsShip(StratoShip ship) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    if (positions[x][y][z].getShip() == ship) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Metodo per ottenere la posizione di una nave sulla board
     * @param ship
     * @return la posizione della nave, null se non trovata
     */
    public Position getShipPosition(StratoShip ship) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    if (positions[x][y][z].getShip() == ship) {
                        return positions[x][y][z];
                    }
                }
            }
        }
        return null;
    }

    /**
     * Metodo per ottenere tutte le posizioni che contengono una nave
     * @return una lista di posizioni che contengono una nave
     */
    public List<Position> getAllPositionsWithShip(){
        List<Position> occupiedPositions = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < length; y++) {
                for (int z = 0; z < levels; z++) {
                    if (positions[x][y][z].getShip() != null) {
                        occupiedPositions.add(positions[x][y][z]);
                    }
                }
            }
        }
        return occupiedPositions;
    }

    
}