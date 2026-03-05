package stratostrike.local;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * JPanel con sfondo personalizzato caricato da resources.
 * Tutte le schermate della GUI estendono questa classe.
 *
 * L'immagine di background va inserita in:
 *   app/src/main/resources/sprite/background.png
 */
public class BackgroundPanel extends JPanel {

    private static final String BG_PATH = "/sprite/background.png";

    private Image bgImage;

    public BackgroundPanel() {
        setOpaque(true);
        loadBackground(BG_PATH);
    }

    /** Costruttore che accetta un percorso custom (es. per schermate diverse). */
    public BackgroundPanel(String resourcePath) {
        setOpaque(true);
        loadBackground(resourcePath);
    }

    private void loadBackground(String path) {
        URL url = getClass().getResource(path);
        if (url != null) {
            try {
                bgImage = ImageIO.read(url);
            } catch (IOException e) {
                System.err.println("[BackgroundPanel] Impossibile caricare l'immagine: " + path);
                bgImage = null;
            }
        } else {
            System.err.println("[BackgroundPanel] Risorsa non trovata: " + path);
            bgImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (bgImage != null) {
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
