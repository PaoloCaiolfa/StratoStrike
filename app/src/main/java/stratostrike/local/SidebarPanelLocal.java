package stratostrike.local;

import java.awt.Color;
import java.util.List;
import java.util.function.Consumer;

import javax.swing.JPanel;

import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Settings;
import stratostrike.Domain.Model.Action.Action;

import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.Box;


public class SidebarPanelLocal extends JPanel{
    Consumer<Integer> onShipSelected;
    Consumer<Integer> onActionSelected;

    private JPanel shipContainer;
    private JPanel actionContainer;

    public SidebarPanelLocal(Consumer<Integer> onShipSelected, Consumer<Integer> onActionSelected) {
        this.onShipSelected = onShipSelected;
        this.onActionSelected = onActionSelected;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Settings.CONTRAST, 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        shipContainer = new JPanel();
        shipContainer.setLayout(new BoxLayout(shipContainer, BoxLayout.Y_AXIS));
        shipContainer.setBorder(BorderFactory.createTitledBorder(
            null, "Ships", 0, 0, null, Color.WHITE
        ));
        shipContainer.setOpaque(false);

        actionContainer = new JPanel();
        actionContainer.setLayout(new BoxLayout(actionContainer, BoxLayout.Y_AXIS));
        actionContainer.setBorder(BorderFactory.createTitledBorder(
            null, "Actions", 0, 0, null, Color.WHITE
        ));
        actionContainer.setOpaque(false);

        JScrollPane shipScrollPane = new JScrollPane(shipContainer);
        shipScrollPane.setBorder(null);
        shipScrollPane.setOpaque(false);
        shipScrollPane.getViewport().setOpaque(false);
        JScrollPane actionScrollPane = new JScrollPane(actionContainer);
        actionScrollPane.setBorder(null);
        actionScrollPane.setOpaque(false);
        actionScrollPane.getViewport().setOpaque(false);

        add(shipScrollPane);
        add(actionScrollPane);
    }

    public void updateSidebar(List<StratoShip> shipNames, int selectedShipIndex) {
        shipContainer.removeAll(); // Rimuove solo i componenti dentro il container delle navi
        actionContainer.removeAll(); // Rimuove solo i componenti dentro il container delle azioni

        showShipOptions(shipNames, selectedShipIndex);

        revalidate(); // Aggiorna il layout del pannello
        repaint(); // Ridisegna il pannello
    }

    public void showShipOptions(List<StratoShip> shipNames, int selectedShipIndex) {

        for (int i = 0; i < shipNames.size(); i++) {
            GenericCardLocal card = new GenericCardLocal(
                i, 
                shipNames.get(i).toString(), 
                onShipSelected
            );

            if (i == selectedShipIndex) {
                card.setHighlighted(true);
            }

            shipContainer.add(card);

            if (i < shipNames.size() - 1) {
                shipContainer.add(Box.createVerticalStrut(8));
            }
        }
    }

    public void showActionOptions(List<Action> actionNames, int selectedActionIndex) {

        for (int i = 0; i < actionNames.size(); i++) {
            GenericCardLocal card = new GenericCardLocal(
                i, 
                actionNames.get(i).toString(), 
                onActionSelected
            );

            if (i == selectedActionIndex) {
                card.setHighlighted(true);
            }

            actionContainer.add(card);

            if (i < actionNames.size() - 1) {
                actionContainer.add(Box.createVerticalStrut(8));
            }
        }
    }
    
}
