package stratostrike.GuiView;

public class NavigationControllerLocal {

    private MainFrameLocal mainFrame;

    /**
     * Costruttore per NavigationControllerLocal.
     * Il Navvigation controller gestisce la navigazione tra le schermate del gioco, interagendo con il MainFrameLocal per mostrare la schermata corretta in base agli eventi del gioco. Viene passato il MainFrameLocal come dipendenza per poter controllare la visualizzazione delle schermate.
     * @param mainFrame
     */
    public NavigationControllerLocal(MainFrameLocal mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Naviga verso la schermata specificata.
     * @param screenName il nome della schermata verso cui navigare
     */
    public void navigateTo(String screenName) {
        mainFrame.showScreen(screenName);
    }

    /* ==================== DEFAULT NAVIGATION ==================== */

    /**
     * Naviga verso la schermata di selezione dell'armata.
     */
    public void navigateToSelection() {
        mainFrame.showScreen("SELECTION");
    }   

    /**
     * Naviga verso la schermata di gioco.
     */
    public void navigateToGame() {
        mainFrame.showScreen("GAME");
    }
    
}
