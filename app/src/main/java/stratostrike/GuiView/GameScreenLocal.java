package stratostrike.GuiView;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import stratostrike.Controller.*;
import stratostrike.Domain.Model.Position;

public class GameScreenLocal 
    extends BackgroundPanel {

    private LoopingTurn loopingTurn;
    private MakeTurn makeTurn;
    private NavigationControllerLocal navigator;

    private BoardPanelLocal boardPanel;
    private SidebarPanelLocal sidebar;
    private MessagePanelLocal messagePanel;

    private int selectedShipIndex;
    private int selectedActionIndex;

    public GameScreenLocal(LoopingTurn loopingTurn, MakeTurn makeTurn, NavigationControllerLocal navigator) {
        // Inizializza il pannello di gioco con i dati necessari
        this.loopingTurn = loopingTurn;
        this.makeTurn = makeTurn;
        this.navigator = navigator;

        setLayout(new BorderLayout());

        boardPanel = new BoardPanelLocal(this::handleCellClick, makeTurn.getBoard());
        messagePanel = new MessagePanelLocal(
            makeTurn.getViewData().getMessage(),
            makeTurn.getViewData().getErrorMessage(),
            makeTurn.getGame().getPlayersName()
        );
        sidebar = new SidebarPanelLocal(
            this::handleShipSelected,
            this::handleActionSelected
        );

        JPanel mainBoardContainer = new JPanel(new BorderLayout());
        mainBoardContainer.setOpaque(false);
        mainBoardContainer.setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        mainBoardContainer.add(boardPanel, BorderLayout.CENTER);
        mainBoardContainer.add(messagePanel, BorderLayout.SOUTH);

        add(mainBoardContainer, BorderLayout.CENTER);
        add(sidebar, BorderLayout.EAST);
        revalidate();
        repaint();
    }

    // -----------------------------------------------------------------------
    // Metodi pubblici usati da GuiEventDispatcher e dagli handler
    // -----------------------------------------------------------------------

    /**
     * Aggiorna board, pannello messaggi e sidebar con lo stato corrente del gioco.
     */
    public void refreshView() {
        boardPanel.updateBoard(
            makeTurn.getBoard(),
            makeTurn.getViewData().getAreaEffect(),
            makeTurn.getGame().getContext().getSelectedShip(),
            makeTurn.getGame().getPlayers()
        );
        messagePanel.updateMessage(
            makeTurn.getViewData().getMessage(),
            makeTurn.getViewData().getErrorMessage()
        );
        int activePlayerIndex = makeTurn.getGame().getPlayers()
            .indexOf(makeTurn.getGame().getContext().getCurrentPlayer());
        messagePanel.setActivePlayer(activePlayerIndex);
        sidebar.updateSidebar(
            makeTurn.getViewData().getAlivePlayerArmy(),
            this.selectedShipIndex
        );
    }

    /**
     * Mostra le azioni disponibili nella sidebar (da chiamare dopo refreshView())
     */
    public void showActionOptions() {
        sidebar.showActionOptions(
            makeTurn.getViewData().getAvailableActions(),
            this.selectedActionIndex
        );
        sidebar.revalidate();
        sidebar.repaint();
    }

    /**
     * Mostra il tasto di conferma nel pannello messaggi.
     */
    public void showConfirmation(Runnable onConfirm) {
        messagePanel.showConfirmation(onConfirm);
    }

    /**
     * Nasconde il tasto di conferma nel pannello messaggi.
     */
    public void hideConfirmation() {
        messagePanel.hideConfirmation();
    }

    // -----------------------------------------------------------------------
    // Callback UI (click handlers)
    // -----------------------------------------------------------------------

    private void handleCellClick(Position pos) {
        ArrayList<Integer> coords = new ArrayList<>();
        coords.add(pos.getX());
        coords.add(pos.getY());
        coords.add(pos.getZ());
        // Logica per gestire il click su una cella della griglia di gioco
        // Puoi utilizzare makeTurn per eseguire le azioni necessarie in base alla posizione cliccata
        makeTurn.selectTarget(coords);
    }

    private void handleShipSelected(int shipIndex) {
        // Logica per gestire la selezione di una nave da parte del giocatore
        // Puoi utilizzare makeTurn per eseguire le azioni necessarie in base alla nave selezionata
        this.selectedShipIndex = shipIndex;
        makeTurn.selectShip(shipIndex);
    }

    private void handleActionSelected(int actionIndex) {
        // Logica per gestire la selezione di un'azione da parte del giocatore
        // Puoi utilizzare makeTurn per eseguire le azioni necessarie in base all'azione selezionata
        this.selectedActionIndex = actionIndex;
        makeTurn.selectAction(actionIndex);
    }

    
}
