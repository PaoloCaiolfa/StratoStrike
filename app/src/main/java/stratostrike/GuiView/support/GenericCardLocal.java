package stratostrike.GuiView.support;

import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import stratostrike.Settings;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class GenericCardLocal extends JPanel {

    private boolean isHighlighted = false;
    

    /**
     * Costruttore per GenericCardLocal.
     * Questa classe rappresenta una card generica utilizzata sia per visualizzare le navi nella sidebar che le azioni disponibili. Il costruttore accetta un indice (per identificare univocamente la card), un'etichetta da visualizzare e un callback onClick che viene chiamato quando la card viene cliccata, passando l'indice come argomento. La card ha uno stile predefinito con un bordo, un colore di sfondo e un font, e include un effetto hover per migliorare l'interazione dell'utente.
     * @param index l'indice univoco della card
     * @param label l'etichetta da visualizzare sulla card
     * @param onClick il callback da chiamare quando la card viene cliccata,
     */
    public GenericCardLocal(int index, String label, Consumer<Integer> onClick) {
        
        // Imposta le dimensioni, il layout, il bordo e lo sfondo della card
        setPreferredSize(new Dimension(300, 60));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Settings.PRIMARY.darker(), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));        
        setBackground(Settings.PRIMARY);

        // Aggiunge un'etichetta centrata con il testo specificato
        JLabel nameLabel = new JLabel(label, SwingConstants.CENTER);
        nameLabel.setFont(new Font(Settings.MAIN_FONT, Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);

        add(nameLabel, BorderLayout.CENTER);

        // Aggiunge un mouse listener per gestire l'effetto hover e il click sulla card
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Settings.PRIMARY.brighter());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!isHighlighted) {
                    setBackground(Settings.PRIMARY);
                } else {
                    setBackground(Settings.CONTRAST.darker());
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                onClick.accept(index);
            }
        });
        
    }

    /**
     * Imposta lo stato di evidenziazione della card quando viene selezionata.
     * @param highlighted lo stato di evidenziazione
     * @return la card stessa
     */
    public GenericCardLocal setHighlighted(boolean highlighted) {
        this.isHighlighted = highlighted;
        if (highlighted) {
            setBackground(Settings.CONTRAST.darker());
        } else {
            setBackground(Settings.PRIMARY);
        }
        return this;
    }
}



