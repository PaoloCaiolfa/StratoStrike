package stratostrike.GuiView.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import stratostrike.Settings;

import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;

public class MessagePanelLocal extends JPanel {
    JLabel messageLabel;
    JLabel errorMessage;
    JButton confirmButton;
    JButton endTurnButton;
    JPanel dialogBox;
    JPanel textPanel;
    ArrayList<JPanel> playerBoxes;

    private static final Color COLOR_ACTIVE_BG   = Settings.PRIMARY;
    private static final Color COLOR_ACTIVE_FG   = Color.WHITE;
    private static final Color COLOR_ACTIVE_BORDER = Settings.PRIMARY.brighter();
    private static final Color COLOR_INACTIVE_BG   = Settings.PRIMARY.darker();
    private static final Color COLOR_INACTIVE_FG   = Settings.TERTIARY;
    private static final Color COLOR_INACTIVE_BORDER = Settings.CONTRAST;

    public MessagePanelLocal(String initialMessage, String initialErrorMessage, ArrayList<String> playersName) {
        setLayout(new BorderLayout());
        setOpaque(false);

        // Outer transparent padding
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        playerBoxes = new ArrayList<>();

        JPanel playersPanel = new JPanel();
        playersPanel.setLayout(new BoxLayout(playersPanel, BoxLayout.X_AXIS));
        playersPanel.setOpaque(false);
        playersPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 8, 5));

        for (int i = 0; i < playersName.size(); i++) {
            JPanel box = buildPlayerBox(playersName.get(i));
            playerBoxes.add(box);
            playersPanel.add(box);

            if (i < playersName.size() - 1) {
                playersPanel.add(Box.createHorizontalStrut(16));
            }
        }

        dialogBox = new JPanel();
        dialogBox.setLayout(new BorderLayout());
        dialogBox.setBackground(Settings.PRIMARY);
        dialogBox.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Settings.CONTRAST, 2),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)
            )
        );

        // Message labels
        messageLabel = new JLabel("<html>" + initialMessage + "</html>");
        messageLabel.setForeground(Color.WHITE);
        messageLabel.setFont(new Font(Settings.MAIN_FONT, Font.PLAIN, 22));

        errorMessage = new JLabel("<html>" + initialErrorMessage + "</html>");
        errorMessage.setForeground(new Color(255, 120, 120));
        errorMessage.setFont(new Font(Settings.MAIN_FONT, Font.PLAIN, 22));

        // Confirm button
        confirmButton = new JButton("Conferma");
        confirmButton.setVisible(false);
        confirmButton.setFont(new Font(Settings.MAIN_FONT, Font.BOLD, 20));

        // Vertical text container
        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        textPanel.add(messageLabel);
        textPanel.add(Box.createVerticalStrut(10));
        textPanel.add(Box.createVerticalStrut(5));
        textPanel.add(errorMessage);
        textPanel.add(Box.createVerticalStrut(5));

        // Add elements to dialog box
        dialogBox.add(textPanel, BorderLayout.CENTER);
        dialogBox.add(confirmButton, BorderLayout.EAST);

        // Fine turno button
        endTurnButton = new JButton("Fine Turno");
        endTurnButton.setFont(new Font(Settings.MAIN_FONT, Font.BOLD, 18));

        JPanel endTurnPanel = new JPanel(new BorderLayout());
        endTurnPanel.setOpaque(false);
        endTurnPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
        endTurnPanel.add(endTurnButton, BorderLayout.EAST);

        JPanel northWrapper = new JPanel();
        northWrapper.setLayout(new BoxLayout(northWrapper, BoxLayout.Y_AXIS));
        northWrapper.setOpaque(false);
        northWrapper.add(endTurnPanel);
        northWrapper.add(playersPanel);

        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.setOpaque(false);
        centerWrapper.add(dialogBox);

        // Add dialog box to main panel
        add(northWrapper, BorderLayout.NORTH);
        add(centerWrapper, BorderLayout.CENTER);
    }

    public void setMessage(String message) {
        messageLabel.setText("<html>" + message + "</html>");
    }

    public void setErrorMessage(String message) {
        errorMessage.setText("<html>" + message + "</html>");
    }

    public void clearMessages() {
        messageLabel.setText("");
        errorMessage.setText("");
    }

    /**
     * Evidenzia il box del giocatore attivo e decolora gli altri.
     * @param activeIndex indice del giocatore corrente (0-based)
     */
    public void setActivePlayer(int activeIndex) {
        for (int i = 0; i < playerBoxes.size(); i++) {
            JPanel box = playerBoxes.get(i);
            JLabel label = (JLabel) box.getComponent(0);
            if (i == activeIndex) {
                box.setBackground(COLOR_ACTIVE_BG);
                box.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Settings.TEAM_COLORS[activeIndex], 2),
                    BorderFactory.createEmptyBorder(6, 14, 6, 14)
                ));
                label.setForeground(COLOR_ACTIVE_FG);
            } else {
                box.setBackground(COLOR_INACTIVE_BG);
                box.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Settings.TEAM_COLORS[i], 1),
                    BorderFactory.createEmptyBorder(6, 14, 6, 14)
                ));
                label.setForeground(COLOR_INACTIVE_FG);
            }
        }
        revalidate();
        repaint();
    }

    /** Costruisce il box stilizzato per un giocatore (stato iniziale inattivo). */
    private JPanel buildPlayerBox(String name) {
        JPanel box = new JPanel(new BorderLayout());
        box.setOpaque(true);
        box.setBackground(COLOR_INACTIVE_BG);
        box.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(COLOR_INACTIVE_BORDER, 1),
            BorderFactory.createEmptyBorder(6, 14, 6, 14)
        ));

        JLabel label = new JLabel(name);
        label.setFont(new Font(Settings.MAIN_FONT, Font.BOLD, 18));
        label.setForeground(COLOR_INACTIVE_FG);
        box.add(label, BorderLayout.CENTER);
        return box;
    }

    public void updateMessage(String message, String errorMessage) {
        setMessage(message);
        setErrorMessage(errorMessage);
    }

    public void showConfirmation(Runnable onConfirm) {
        confirmButton.setVisible(true);

        for (ActionListener al : confirmButton.getActionListeners()) {
            confirmButton.removeActionListener(al);
        }

        confirmButton.addActionListener(e -> onConfirm.run());
    }

    public void hideConfirmation() {
        confirmButton.setVisible(false);
        for (ActionListener al : confirmButton.getActionListeners()) {
            confirmButton.removeActionListener(al);
        }
    }

    public void setEndTurnAction(ActionListener onEndTurn) {
        for (ActionListener al : endTurnButton.getActionListeners()) {
            endTurnButton.removeActionListener(al);
        }
        endTurnButton.addActionListener(onEndTurn);
    }
}
