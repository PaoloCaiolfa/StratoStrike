package stratostrike.GuiView.support;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import stratostrike.Settings;

public class SpriteLocal {


    private static final int BORDER = 2;      

    private BufferedImage image;
    private int col, row, z;
    private int playerIndex; 

    /**
     * Costruttore per SpriteLocal senza filtro team.
     * @param path
     * @param col
     * @param row
     * @param z
     */
    public SpriteLocal(String path, int col, int row, int z) {
        this(path, col, row, z, -1);
    }

    /**
     * Costruttore per SpriteLocal con filtro team.
     * @param path
     * @param col
     * @param row
     * @param z
     * @param playerIndex
     */
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

    /**
     * Disegna lo sprite sulla griglia di gioco, applicando un filtro colorato se playerIndex è valido.
     * @param g
     * @param cellSize
     * @param boardOffsetZ
     */
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

    /**
     * Disegna un bordo colorato + sfondo semi-trasparente attorno alla cella.
     * @param g
     * @param px
     * @param py
     * @param cellSize
     */
    private void drawTeamFilter(Graphics g, int px, int py, int cellSize) {
        Graphics2D g2 = (Graphics2D) g;
        Color teamColor = Settings.TEAM_COLORS[playerIndex];

        g2.setColor(teamColor);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(2f));
        g2.drawArc(px, py, cellSize, cellSize, 0, 360);
    }
}

