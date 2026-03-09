package stratostrike.GuiView;

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

    private static final int MIN_CELL_SIZE = 16;
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
        int minW = (MIN_CELL_SIZE * Settings.BoardLengthStandard * 2) + BOARD_OFFSET_Z;
        int minH = MIN_CELL_SIZE * Settings.BoardWidthStandard;
        setMinimumSize(new Dimension(minW, minH));
        setPreferredSize(new Dimension(minW * 2, minH * 2));
        setOpaque(false);

        sprites = new ArrayList<>();
    }


    private int getCurrentCellSize() {
        int cols = Settings.BoardLengthStandard * 2;
        int rows = Settings.BoardWidthStandard;
        int availableW = getWidth() - BOARD_OFFSET_Z;
        int availableH = getHeight();
        if (availableW <= 0 || availableH <= 0) return MIN_CELL_SIZE;
        int fromW = availableW / cols;
        int fromH = availableH / rows;
        return Math.max(MIN_CELL_SIZE, Math.min(fromW, fromH));
    }

    private void drawBoardBackground(Graphics g, int z, int cs) {
        Graphics2D g2d = (Graphics2D) g;
        int boardWidth = Settings.BoardLengthStandard * cs;
        int boardHeight = Settings.BoardWidthStandard * cs;
        int offsetX = z * (boardWidth + BOARD_OFFSET_Z);

        if (z == 0) {
            g2d.setColor(new Color(135, 206, 230)); // cielo
        } else {
            g2d.setColor(new Color(15, 15, 40)); // spazio
        }

        g2d.fillRect(offsetX, 0, boardWidth, boardHeight);
    }

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

    private void drawTitles(Graphics g, int cs) {
        int boardWidth = Settings.BoardLengthStandard * cs;
        int boardHeight = Settings.BoardWidthStandard * cs;
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 16));

        g.drawString("CIELO", 20, boardHeight + 20);
        g.drawString("SPAZIO", boardWidth + BOARD_OFFSET_Z + 20, boardHeight + 20);
    }

    private void drawSprites(Graphics g, int cs) {
        for (SpriteLocal sprite : sprites) {
            sprite.draw(g, cs, BOARD_OFFSET_Z);
        }
    }

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
                        pos.getX(), pos.getY(), pos.getZ()));
                }
            }
        );

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
