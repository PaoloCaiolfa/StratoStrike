package stratostrike.GuiView.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import stratostrike.Settings;
import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.GuiView.support.SpriteLocal;

public class BoardPanelLocal extends JPanel {

    private static final int MIN_CELL_SIZE = 16;
    private static final int BOARD_OFFSET_Z = 20;

    private List<SpriteLocal> sprites; 
    private Consumer<Position> onCellClick;
    private Board boardInfos;


    public BoardPanelLocal(Consumer<Position> onCellClick, Board boardInfos) {
        this.onCellClick = onCellClick;
        this.boardInfos = boardInfos;

        /* Aggiunge un mouse listener per gestire i click sulle celle della griglia */
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int cs = getCurrentCellSize();
                int totalBoardWidth = Settings.BoardLengthStandard * cs + BOARD_OFFSET_Z;

                int z = e.getX() / totalBoardWidth;

                int localX = e.getX() - (z * totalBoardWidth);

                int cellX = localX / cs;
                int cellY = e.getY() / cs;

                if (z < 0 || z > 1) return;
                if (cellX < 0 || cellX >= Settings.BoardLengthStandard) return;
                if (cellY < 0 || cellY >= Settings.BoardWidthStandard) return;

                onCellClick.accept(new Position(cellX, cellY, z));
            }
        });

        // Inizializza il pannello di gioco — la preferred size è il minimo
        // La dimensione minima è calcolata in modo da garantire che ogni cella sia almeno MIN_CELL_SIZE x MIN_CELL_SIZE, più lo spazio per il bordo e l'offset tra i due piani
        int minW = (MIN_CELL_SIZE * Settings.BoardLengthStandard * 2) + BOARD_OFFSET_Z;
        int minH = MIN_CELL_SIZE * Settings.BoardWidthStandard;
        setMinimumSize(new Dimension(minW, minH));
        setPreferredSize(new Dimension(minW * 2, minH * 2));
        setOpaque(false);

        sprites = new ArrayList<>();
    }

    /**
     * Restituisce la dimensione corrente di una cella della griglia.
      * La dimensione è calcolata in modo da adattarsi alla dimensione del pannello, mantenendo le proporzioni corrette e garantendo una dimensione minima.
     */
    private int getCurrentCellSize() {
        // Rows e cols sono il numero totale di righe e colonne considerando entrambi i piani (cielo e spazio)
        int cols = Settings.BoardLengthStandard * 2;
        int rows = Settings.BoardWidthStandard;

        // availableW e availableH sono lo spazio disponibile per disegnare la griglia, considerando la dimensione corrente della schermata e sottraendo l'offset tra i due piani
        int availableW = getWidth() - BOARD_OFFSET_Z;
        int availableH = getHeight();

        // Controllo per la dimensione minima
        if (availableW <= 0 || availableH <= 0) return MIN_CELL_SIZE;
        int fromW = availableW / cols;
        int fromH = availableH / rows;

        return Math.max(MIN_CELL_SIZE, Math.min(fromW, fromH));
    }

    /**
     * Disegna lo sfondo della griglia per uno specifico piano.
     */
    private void drawBoardBackground(Graphics g, int z, int cs) {
        Graphics2D g2d = (Graphics2D) g;

        // Calcola la posizione e le dimensioni del piano da disegnare
        int boardWidth = Settings.BoardLengthStandard * cs;
        int boardHeight = Settings.BoardWidthStandard * cs;
        int offsetX = z * (boardWidth + BOARD_OFFSET_Z);

        // Imposta il colore di sfondo in base al piano (cielo o spazio)
        if (z == 0) {
            g2d.setColor(new Color(135, 206, 230)); // cielo
        } else {
            g2d.setColor(new Color(15, 15, 40)); // spazio
        }

        g2d.fillRect(offsetX, 0, boardWidth, boardHeight);
    }

    /**
     * Disegna il componente con tutte le sue parti: sfondo, griglia, sprite e titoli.
      * Il metodo viene chiamato automaticamente da Swing ogni volta che è necessario ridisegnare il pannello (ad esempio dopo un aggiornamento dello stato del gioco).
      * La dimensione delle celle e la posizione degli elementi vengono calcolati dinamicamente in base alla dimensione corrente del pannello, garantendo un layout adattivo e proporzionato.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cs = getCurrentCellSize();

        drawBoardBackground(g, 0, cs);
        drawBoardBackground(g, 1, cs);

        drawGrid(g, 0, cs);
        drawGrid(g, 1, cs);

        drawSprites(g, cs);
        drawTitles(g, cs);
    }

    /**
     * Disegna i titoli per i due piani della griglia.
      * I titoli "CIELO" e "SPAZIO" vengono disegnati sotto i rispettivi piani, con un font chiaro e ben visibile.
     */
    private void drawTitles(Graphics g, int cs) {
        int boardWidth = Settings.BoardLengthStandard * cs;
        int boardHeight = Settings.BoardWidthStandard * cs;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));

        g.drawString("CIELO", 20, boardHeight + 20);
        g.drawString("SPAZIO", boardWidth + BOARD_OFFSET_Z + 20, boardHeight + 20);
    }

    /**
     * Disegna i sprite sulla griglia.
     */
    private void drawSprites(Graphics g, int cs) {
        for (SpriteLocal sprite : sprites) {
            sprite.draw(g, cs, BOARD_OFFSET_Z);
        }
    }

    /**
     * Disegna la griglia.
     */
    private void drawGrid(Graphics g, int zOffset, int cs) {
        g.setColor(Color.GRAY);

        int boardWidth = Settings.BoardLengthStandard * cs;
        int offsetX = zOffset * (boardWidth + BOARD_OFFSET_Z);

        for (int i = 0; i <= Settings.BoardLengthStandard; i++) {
            g.drawLine(
                offsetX + i * cs, 0,
                offsetX + i * cs, Settings.BoardWidthStandard * cs
            );
            g.drawLine(
                offsetX, i * cs,
                offsetX + boardWidth, i * cs
            );
        }
    }

    /**
     * Verifica se una posizione è all'interno della griglia.
      * @param pos la posizione da verificare
      * @return true se la posizione è all'interno della griglia, false altrimenti
     */
    public boolean inBoard(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        return x >= 0 && x < Settings.BoardLengthStandard &&
               y >= 0 && y < Settings.BoardWidthStandard;
    }

    /**
     * Aggiorna il tabellone con le nuove informazioni.
     * @param newBoard il nuovo tabellone
     * @param highlightedPositions le posizioni evidenziate
     * @param selectedShip la nave selezionata
     * @param players i giocatori del gioco (usati per determinare il colore dei sprite)
      * Il metodo viene richiamato da GameScreenLocal ogni volta che lo stato del gioco cambia e la visualizzazione deve essere aggiornata.
     */
    public void updateBoard(Board newBoard, ArrayList<Position> highlightedPositions, StratoShip selectedShip, ArrayList<Player> players) {
        this.boardInfos = newBoard;
        sprites.clear();

        // Se ci sono i range attivi, evidenzia le celle
        highlightedPositions.forEach(pos -> {
                if (inBoard(pos)) {
                    sprites.add(new SpriteLocal(
                        "/sprite/highlight.png",
                        pos.getX(), pos.getY(), pos.getZ()));
                }
            }
        );

        // Aggiunge i sprite per le navi presenti sulla board, con il filtro colore in base al giocatore proprietario
        for (Position pos : boardInfos.getAllPositionsWithShip()) {
            int playerIndex = getPlayerIndex(pos.getShip(), players);
            if (selectedShip != null && pos.getShip() == selectedShip) {
                sprites.add(new SpriteLocal(
                    "/sprite/highlight.png",
                    pos.getX(), pos.getY(), pos.getZ()));
            }
            sprites.add(new SpriteLocal(
                pos.getShip().isDestroyed()
                    ? "/sprite/DEAD_" + pos.getShip().getName() + ".png"
                    : "/sprite/" + pos.getShip().getName() + ".png",
                pos.getX(), pos.getY(), pos.getZ(),
                playerIndex));
        }

        repaint();
    }

    /**
     * Ritorna l'indice del giocatore proprietario della nave, -1 se non trovato.
     * Il metodo viene utilizzato per determinare il colore del sprite da disegnare sulla griglia, in base al giocatore a cui appartiene la nave. Se la nave non è associata a nessun giocatore, viene restituito -1 e il sprite viene disegnato con un colore neutro.
      * @param ship la nave di cui si vuole conoscere il proprietario
      * @param players la lista dei giocatori del gioco
      * @return l'indice del giocatore proprietario della nave, o -1 se non trovato
     */
    private int getPlayerIndex(StratoShip ship, ArrayList<Player> players) {
        if (players == null) return -1;
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getArmy() != null &&
                players.get(i).getArmy().getShips().contains(ship)) {
                return i;
            }
        }
        return -1;
    }
    
}
