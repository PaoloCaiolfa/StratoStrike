package stratostrike.local;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrameLocal extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public MainFrameLocal() {
        setTitle("StratoStrike - Local Mode");
        setSize(1980, 1080);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        setContentPane(mainPanel);
    }

    public void addScreen(String name, JPanel screen) {
        mainPanel.add(screen, name);
    }   

    public void showScreen(String name) {
        cardLayout.show(mainPanel, name);
    }
    
}
