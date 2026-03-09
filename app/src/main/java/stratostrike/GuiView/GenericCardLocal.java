package stratostrike.GuiView;

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
    
    public GenericCardLocal(int index, String label, Consumer<Integer> onClick) {
        
        setPreferredSize(new Dimension(300, 60));
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Settings.PRIMARY.darker(), 2),
            BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));        
        setBackground(Settings.PRIMARY);


        JLabel nameLabel = new JLabel(label, SwingConstants.CENTER);
        nameLabel.setFont(new Font(Settings.MAIN_FONT, Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);

        add(nameLabel, BorderLayout.CENTER);

        // Hover effect
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



