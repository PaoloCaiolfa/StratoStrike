package stratostrike.Controller;

import javax.swing.SwingUtilities;

import java.util.ArrayList;

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


/**
 * Classe principale che si occupa di inizializzare il gioco, i giocatori, il controller e la view, e di avviare il gioco in base alle impostazioni scelte (GUI o console)
 */
public class GameLauncher {

    public void launchGame() {

        // Crea i giocatori, per il momento sono hardcoded, ma in futuro si implementerà una schemata di selezione di quanti giocatori partecipano e dei loro nomi
        Player player1 = new Player("Player1",0);
        Player player2 = new Player("Player2",1);

        ArrayList<Player> players = new ArrayList<>();

        players.add(player1);
        players.add(player2);

        // Crea il gioco con i giocatori specificati
        StratoCraftGame game = new StratoCraftGame(players);
        game.setCurrentEvent(GameEvent.SELECT_ARMY);
        System.out.println("Welcome to " + game.getPlayer(0).getUsername() + " vs " + game.getPlayer(1).getUsername()
                + " Stratostrike Game!");


        LoopingTurn turn = new LoopingTurn(game);
        SetupArmy setupArmy = new SetupArmy(game);


        // Se è abilitata la GUI, inizializza e mostra la GUI, altrimenti avvia il gioco in console
        // La GUI e la console condividono la stessa logica di gioco, implementata nei controller, e si aggiornano a vicenda tramite un sistema di observer e dispatcher
        // Il valore USE_GUI è definito in Settings e può essere modificato per scegliere se avviare la GUI o la console
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
            new EventDispatcher(
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
