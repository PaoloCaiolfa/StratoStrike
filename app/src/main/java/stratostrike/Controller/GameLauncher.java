package stratostrike.Controller;

import javax.swing.SwingUtilities;

import stratostrike.GameEvent;
import stratostrike.Settings;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.GuiView.MainFrameLocal;
import stratostrike.GuiView.NavigationControllerLocal;
import stratostrike.GuiView.EventHandlers.GuiEventDispatcher;
import stratostrike.GuiView.screens.GameScreenLocal;
import stratostrike.GuiView.screens.SelectionScreenLocal;
import stratostrike.View.SelectionView;
import stratostrike.View.GameOutputView;
import stratostrike.View.SetupOutputView;
import stratostrike.View.EventHandlers.EventDispatcher;

public class GameLauncher {

    public void launchGame() {

        // per ora è tutto hardcodato, ma almeno iniziamo a definire il main controller e dove inizia il flusso del gioco

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


        if (Settings.USE_GUI) {
            SwingUtilities.invokeLater(() -> {
                MainFrameLocal frame = new MainFrameLocal();
                NavigationControllerLocal navigator = new NavigationControllerLocal(frame);

                SelectionScreenLocal selection = new SelectionScreenLocal(setupArmy);
                GameScreenLocal gameScreen = new GameScreenLocal(turn, turn.getMakeTurn(), navigator);

                frame.addScreen("SELECTION", selection);
                frame.addScreen("GAME", gameScreen);

                // Il dispatcher si registra come observer e instrada ogni evento
                // all'handler GUI appropriato
                new GuiEventDispatcher(
                    turn.getMakeTurn(),
                    setupArmy,
                    turn,
                    selection,
                    gameScreen,
                    navigator
                );

                navigator.navigateToSelection();

                frame.setVisible(true);
            });
        } else {


            SelectionView selectionView = new SelectionView(turn.getMakeTurn(), setupArmy);
            GameOutputView gameOutputView = new GameOutputView();
            SetupOutputView setupOutputView = new SetupOutputView();
        
            // Crea il dispatcher che osserva i controller
            EventDispatcher eventDispatcher = new EventDispatcher(
                turn.getMakeTurn(), 
                setupArmy, 
                selectionView, 
                gameOutputView, 
                setupOutputView
            );
            // Avvia il flusso del gioco in console
            setupArmy.selectionForAllPlayer();
            turn.startMatch();
        }

    }
    
}
