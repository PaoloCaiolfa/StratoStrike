package stratostrike.local;

public class NavigationControllerLocal {

    private MainFrameLocal mainFrame;

    public NavigationControllerLocal(MainFrameLocal mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void navigateTo(String screenName) {
        mainFrame.showScreen(screenName);
    }

    public void navigateToSelection() {
        mainFrame.showScreen("SELECTION");
    }   

    public void navigateToGame() {
        mainFrame.showScreen("GAME");
    }
    
}
