package stratostrike.GuiView;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import stratostrike.Settings;
import stratostrike.Controller.SetupArmy;

public class SelectionScreenLocal extends BackgroundPanel {

    private SetupArmy setupArmy;
    private JLabel title;

    public SelectionScreenLocal(SetupArmy setupArmy) {

        this.setupArmy = setupArmy;
        

        // Inizializza i componenti della schermata di selezione
        // Puoi utilizzare setupArmy per accedere alle informazioni necessarie per la selezione
        setLayout(new BorderLayout());

        title = new JLabel("Seleziona l'armata per il giocatore " + setupArmy.getPlayerUsername(), SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        add(title, BorderLayout.NORTH);

        buildCards(setupArmy);
        
    }

    public void refreshTitle() {
        title.setText("Seleziona l'armata per il giocatore " + setupArmy.getPlayerUsername());
        repaint();
    }

    private void buildCards(SetupArmy setupArmy) {
        JPanel cardsPanel = new JPanel();
        cardsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        cardsPanel.setOpaque(false);

        ArrayList<String> armyNames = setupArmy.getArmyNames();

        for (int index = 0; index < Settings.ArmyTipology.size(); index++) {
            String army = Settings.ArmyTipology.get(index);
            GenericCardLocal card = new GenericCardLocal(index, army, (Integer selectedArmyIndex) -> {
                // Logica per gestire la selezione dell'armata
                setupArmy.selectArmy(selectedArmyIndex);
            });
            cardsPanel.add(card);
        }

        for (int index = Settings.ArmyTipology.size(); index < armyNames.size() + Settings.ArmyTipology.size(); index++) {
            String army = armyNames.get(index - Settings.ArmyTipology.size());
            GenericCardLocal card = new GenericCardLocal(index, army, (Integer selectedArmyIndex) -> {
                // Logica per gestire la selezione dell'armata
                setupArmy.selectArmy(selectedArmyIndex);
            });
            cardsPanel.add(card);
        }

        GenericCardLocal customCard = new GenericCardLocal(armyNames.size() + Settings.ArmyTipology.size(), "Composizione Personalizzata", (Integer selectedCustomIndex) -> {
            // Logica per gestire la selezione dell'opzione di composizione personalizzata
            setupArmy.selectArmy(selectedCustomIndex);
        });
        cardsPanel.add(customCard);

        add(cardsPanel, BorderLayout.CENTER);
    }
    
}
