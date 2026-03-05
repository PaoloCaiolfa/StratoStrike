package stratostrike.local;

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

public class BoardPanelLocal extends JPanel {

    private static final int CELL_SIZE = 32;
    private static final int BOARD_HEIGHT = CELL_SIZE * Settings.BoardWidthStandard;
    private static final int BOARD_WIDTH = CELL_SIZE * Settings.BoardLengthStandard;
    private static final int BOARD_OFFSET_Z = 20; 

    private List<SpriteLocal> sprites; // Lista di sprite da disegnare sulla griglia
    private Consumer<Position> onCellClick;
    private Board boardInfos;

    public BoardPanelLocal(Consumer<Position> onCellClick, Board boardInfos) {
        this.onCellClick = onCellClick;
        this.boardInfos = boardInfos;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int totalBoardWidth = BOARD_WIDTH + BOARD_OFFSET_Z;

                int z = e.getX() / totalBoardWidth;

                int localX = e.getX() - (z * totalBoardWidth);

                int cellX = localX / CELL_SIZE;
                int cellY = e.getY() / CELL_SIZE;

                if (z < 0 || z > 1) return;
                if (cellX < 0 || cellX >= Settings.BoardLengthStandard) return;
                if (cellY < 0 || cellY >= Settings.BoardWidthStandard) return;

                onCellClick.accept(new Position(cellX, cellY, z));
            }
        });
        // Inizializza il pannello di gioco
        setPreferredSize(new Dimension(
            (BOARD_WIDTH * 2) + BOARD_OFFSET_Z,
            BOARD_HEIGHT)
        );
        setOpaque(false);

        sprites = new ArrayList<>();
    }


    private void drawBoardBackground(Graphics g, int z) {
        Graphics2D g2d = (Graphics2D) g;
        int offsetX = z * (BOARD_WIDTH + BOARD_OFFSET_Z);

        // Il quarto parametro è l'alpha: 0 = trasparente, 255 = opaco
        if (z == 0) {
            g2d.setColor(new Color(135, 206, 230)); // cielo
        } else {
            g2d.setColor(new Color(15, 15, 40)); // spazio
        }

        g2d.fillRect(
            offsetX,
            0,
            BOARD_WIDTH,
            BOARD_HEIGHT
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Logica per disegnare lo sfondo del pannello di gioco
        drawBoardBackground(g, 0);
        drawBoardBackground(g, 1);

        // Logica per disegnare la griglia di gioco e le navi
        drawGrid(g, 0);
        drawGrid(g, 1);

        drawSprites(g);
        drawTitles(g);
    }

    private void drawTitles(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));

        g.drawString("CIELO", 20, BOARD_HEIGHT + 20);
        g.drawString("SPAZIO", BOARD_WIDTH + BOARD_OFFSET_Z + 20, BOARD_HEIGHT + 20);
    }

    private void drawSprites(Graphics g) {
        for (SpriteLocal sprite : sprites) {
            sprite.draw(g, CELL_SIZE);
        }
    }

    private void drawGrid(Graphics g, int zOffset) {
        g.setColor(Color.GRAY);

        int offsetX = zOffset * (BOARD_WIDTH + BOARD_OFFSET_Z);

        for (int i = 0; i <= Settings.BoardLengthStandard; i++) {

            g.drawLine(
                offsetX + i * CELL_SIZE,
                0,
                offsetX + i * CELL_SIZE,
                Settings.BoardWidthStandard * CELL_SIZE
            );

            g.drawLine(
                offsetX,
                i * CELL_SIZE,
                offsetX + Settings.BoardLengthStandard * CELL_SIZE,
                i * CELL_SIZE
            );
        }
    }

    public boolean inBoard(Position pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        return x >= 0 && x < Settings.BoardLengthStandard &&
               y >= 0 && y < Settings.BoardWidthStandard;
    }

    public void updateBoard(Board newBoard, ArrayList<Position> highlightedPositions, StratoShip selectedShip, ArrayList<Player> players) {
        this.boardInfos = newBoard;
        sprites.clear();

        highlightedPositions.forEach(pos -> {
                if (inBoard(pos)) {
                    sprites.add(new SpriteLocal(
                        "/sprite/highlight.png", 
                        pos.getX() * CELL_SIZE + pos.getZ() * (BOARD_WIDTH + BOARD_OFFSET_Z),
                        pos.getY() * CELL_SIZE)
                    );
                } 
            }
        );

        for (Position pos : boardInfos.getAllPositionsWithShip()) {
            int playerIndex = getPlayerIndex(pos.getShip(), players);
            if (selectedShip != null && pos.getShip() == selectedShip) {
                sprites.add(new SpriteLocal(
                    "/sprite/highlight.png", 
                    pos.getX() * CELL_SIZE + pos.getZ() * (BOARD_WIDTH + BOARD_OFFSET_Z),
                    pos.getY() * CELL_SIZE));
            } 
            sprites.add(new SpriteLocal(
                pos.getShip().isDestroyed() 
                    ? "/sprite/DEAD_" + pos.getShip().getName() + ".png"
                    : "/sprite/" + pos.getShip().getName() + ".png", 
                pos.getX() * CELL_SIZE + pos.getZ() * (BOARD_WIDTH + BOARD_OFFSET_Z),
                pos.getY() * CELL_SIZE,
                playerIndex));
        }

        repaint();
    }

    /** Ritorna l'indice del giocatore proprietario della nave, -1 se non trovato. */
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
