package stratostrike.GuiView;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import stratostrike.Settings;

public class SpriteLocal {

    // Colori team: player 0 → blu, player 1 → verde acqua

    private static final int   BORDER     = 2;      // spessore bordo in pixel

    private BufferedImage image;
    private int col, row, z;
    private int playerIndex; // -1 = nessun filtro

    /** Costruttore senza filtro team (highlight, ecc.) */
    public SpriteLocal(String path, int col, int row, int z) {
        this(path, col, row, z, -1);
    }

    /** Costruttore con filtro team. playerIndex: 0 o 1. */
    public SpriteLocal(String path, int col, int row, int z, int playerIndex) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.col = col;
        this.row = row;
        this.z = z;
        this.playerIndex = playerIndex;
    }

    public void draw(Graphics g, int cellSize, int boardOffsetZ) {
        int px = col * cellSize + z * (Settings.BoardLengthStandard * cellSize + boardOffsetZ);
        int py = row * cellSize;
        if (playerIndex >= 0 && playerIndex < Settings.TEAM_COLORS.length) {
            drawTeamFilter(g, px, py, cellSize);
        }
        if (image != null) {
            g.drawImage(image, px + BORDER, py + BORDER,
                        cellSize - BORDER * 2, cellSize - BORDER * 2, null);
        }
    }

    /** Disegna un bordo colorato + sfondo semi-trasparente attorno alla cella. */
    private void drawTeamFilter(Graphics g, int px, int py, int cellSize) {
        Graphics2D g2 = (Graphics2D) g;
        Color teamColor = Settings.TEAM_COLORS[playerIndex];

        g2.setColor(teamColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.drawArc(px, py, cellSize, cellSize, 0, 360);
    }
}

