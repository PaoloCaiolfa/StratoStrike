package stratostrike.Controller;

import javax.swing.SwingUtilities;

import stratostrike.GameEvent;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.View.OutputView;
import stratostrike.View.SelectionView;
import stratostrike.View.SetupView;
import stratostrike.local.GameScreenLocal;
import stratostrike.local.MainFrameLocal;
import stratostrike.local.NavigationControllerLocal;
import stratostrike.local.SelectionScreenLocal;

public class GameLauncher {

    public void launchGame() {

        // per ora è tutto hardcodadto, ma almeno iniziamo a definire il main controller e dove inizia il flusso del gioco


        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",1);
        java.util.ArrayList<Player> players = new java.util.ArrayList<>();
        players.add(player1);
        players.add(player2);

        //creazione partita
        StratoCraftGame game = new StratoCraftGame(players);
        game.setCurrentEvent(GameEvent.SELECT_ARMY);
        System.out.println("Welcome to " + game.getPlayer(0).getUsername() + " vs " + game.getPlayer(1).getUsername()
                + " Stratostrike Game!");


        LoopingTurn turn = new LoopingTurn(game);
        SetupArmy setupArmy = new SetupArmy(game);

        //SelectionView selection = new SelectionView(turn.getMakeTurn(),setupArmy);
        //SetupView setupView = new SetupView(setupArmy,selection);
        //OutputView outputView = new OutputView(turn.getMakeTurn(),setupArmy ); //porcata
        
        // Creo un'armata nemica e la metto sulla board per test

        // Da commentare

        SwingUtilities.invokeLater(() -> {
            MainFrameLocal frame = new MainFrameLocal();
            NavigationControllerLocal navigator = new NavigationControllerLocal(frame);
            
            SelectionScreenLocal selection = new SelectionScreenLocal(setupArmy, turn, navigator);
            GameScreenLocal gameScreen = new GameScreenLocal(turn, turn.getMakeTurn(), navigator);

            frame.addScreen("SELECTION", selection);
            frame.addScreen("GAME", gameScreen);

            navigator.navigateToSelection();

            frame.setVisible(true);
        });
        

        //setupArmy.notifyObservers();
        //turn.startMatch();
    }
    
}
