package stratostrike.Controller;

import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.View.OutputView;
import stratostrike.View.SelectionView;
import stratostrike.View.SetupView;

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
        System.out.println("Welcome to " + game.getPlayer(0).getUsername() + " vs " + game.getPlayer(1).getUsername()
                + " Stratostrike Game!");


        LoopingTurn turn = new LoopingTurn(game);
        SetupArmy setupArmy = new SetupArmy(game);

        SelectionView selection = new SelectionView(turn.getMakeTurn(),setupArmy);
        SetupView setupView = new SetupView(setupArmy,selection);
        OutputView outputView = new OutputView(turn.getMakeTurn(),setupArmy ); //porcata
        
        // Creo un'armata nemica e la metto sulla board per test
       
        setupArmy.selectionForAllPlayer();
        turn.startMatch();
    }
    
}
