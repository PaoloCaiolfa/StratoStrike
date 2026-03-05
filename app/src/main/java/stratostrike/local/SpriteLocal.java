package stratostrike.local;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class SpriteLocal {

    // Colori team: player 0 → blu, player 1 → verde acqua
    private static final Color[] TEAM_COLORS = {
        new Color(80, 140, 255),   // player 0
        new Color(0, 220, 160),    // player 1
    };
    private static final float TEAM_ALPHA = 0.35f; // trasparenza del filtro (0=invisibile, 1=opaco)
    private static final int   BORDER     = 2;      // spessore bordo in pixel

    private BufferedImage image;
    private int x, y;
    private int playerIndex; // -1 = nessun filtro

    /** Costruttore senza filtro team (highlight, ecc.) */
    public SpriteLocal(String path, int x, int y) {
        this(path, x, y, -1);
    }

    /** Costruttore con filtro team. playerIndex: 0 o 1. */
    public SpriteLocal(String path, int x, int y, int playerIndex) {
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
        this.playerIndex = playerIndex;
    }

    public void draw(Graphics g, int cellSize) {
        if (playerIndex >= 0 && playerIndex < TEAM_COLORS.length) {
            drawTeamFilter(g, cellSize);
        }
        if (image != null) {
            g.drawImage(image, x + BORDER, y + BORDER,
                        cellSize - BORDER * 2, cellSize - BORDER * 2, null);
        }
    }

    /** Disegna un bordo colorato + sfondo semi-trasparente attorno alla cella. */
    private void drawTeamFilter(Graphics g, int cellSize) {
        Graphics2D g2 = (Graphics2D) g;
        Color teamColor = TEAM_COLORS[playerIndex];

        // Bordo pieno
        g2.setColor(teamColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.fillRoundRect(x, y, cellSize, cellSize, 4, 4);

        // Sfondo interno semi-trasparente
        Composite original = g2.getComposite();
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, TEAM_ALPHA));
        g2.setColor(teamColor);
        g2.fillRect(x + BORDER, y + BORDER, cellSize - BORDER * 2, cellSize - BORDER * 2);
        g2.setComposite(original);
    }
}

