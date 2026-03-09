package stratostrike.GuiView;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrameLocal extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    /**
     * Costruisce il MainFrame, ovvero la finestra principale dell'applicazione, che contiene tutte le schermate del gioco. Il MainFrame utilizza un CardLayout per gestire la visualizzazione delle diverse schermate (SelectionScreenLocal, GameScreenLocal, ecc.) e fornisce metodi per aggiungere nuove schermate e per mostrare una schermata specifica. Il MainFrame è il punto di ingresso dell'interfaccia grafica e viene creato e mostrato da GameLauncher quando si avvia il gioco in modalità locale.
     */
    public MainFrameLocal() {
        setTitle("StratoStrike - Local Mode");
        setSize(1980, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        setContentPane(mainPanel);
    }

    /**
     * Aggiunge una nuova schermata al MainFrame.
     * @param name il nome della schermata
     * @param screen il pannello che rappresenta la schermata
     */
    public void addScreen(String name, JPanel screen) {
        mainPanel.add(screen, name);
    }   

    /**
    * Mostra la schermata specificata.
    * @param name il nome della schermata da mostrare
    */
    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }
    
}
