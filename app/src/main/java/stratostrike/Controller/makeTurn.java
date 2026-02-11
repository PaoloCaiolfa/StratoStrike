package stratostrike.Controller;

import java.util.ArrayList;
import java.util.List;
import stratostrike.Domain.Model.Army.*;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.Domain.Model.Action.*;
import stratostrike.Domain.*;
import stratostrike.View.*;
import stratostrike.Input.*;

public class MakeTurn {

    private StratoCraftGame game;

    public MakeTurn(StratoCraftGame game) {
        this.game = game;
    }

    /**
     * Gestisce l'intero turno del giocatore corrente
     */
    public void playTurn() {
        Player current = game.getCurrentPlayer();
        BoardView.printActualPlayer(current);

        ArrayList<StratoShip> army = current.getArmy().getShips();

        BoardView.printBoard(game.getBoard());
        BoardView.printArmy(army, game.getBoard());
    }

    /**
     * Permette al giocatore di selezionare una nave dalla sua armata
     */
    public void selectShip() {
        Player current = game.getCurrentPlayer();
        
        // Usa il metodo esistente che già gestisce tutto
        int selectedIndex = InputView.getShipSelection(current.getArmy());
        
        StratoShip selectedShip = current.getArmy().get(selectedIndex);
        game.setSelectedShip(selectedShip);
        
        // Mostra info sulla nave selezionata
        System.out.println("\n" + "=".repeat(60));
        System.out.println("NAVE SELEZIONATA: " + selectedShip.getName());
        System.out.println("=".repeat(60));
        System.out.println("Tipo: " + selectedShip.getClass().getSimpleName());
        System.out.println("HP: " + selectedShip.getHp());
        System.out.println("Azioni disponibili: " + selectedShip.getActions().size());
        System.out.println("=".repeat(60));
    }

    /**
     * Mostra le azioni disponibili per la nave selezionata
     */
    public void showActions() {
        StratoShip selectedShip = game.getSelectedShip();
        ActionView.showActions(selectedShip.getActions());
    }

    /**
     * Permette al giocatore di selezionare un'azione
     */
    public void selectAction() {
        StratoShip selectedShip = game.getSelectedShip();
        
        // Mostra le azioni con dettagli
        ActionView.showActionsWithDetails(selectedShip.getActions());
        
        // Usa il metodo esistente che già gestisce la validazione
        int selectedIndex = InputView.getActionSelection(selectedShip);
        
        Action selectedAction = selectedShip.getActions().get(selectedIndex);
        game.setSelectedAction(selectedAction);
        
        // Mostra info sull'azione selezionata
        System.out.println("\n" + "=".repeat(60));
        System.out.println("AZIONE SELEZIONATA: " + selectedAction.getName());
        System.out.println("=".repeat(60));
        System.out.print(selectedAction.getDetails());
        System.out.println("=".repeat(60));
    }

    /**
     * Mostra l'area di effetto dell'azione selezionata
     */
    public void showAreaEffect() {
        List<Integer> target = InputView.getPositionTarget(game.getBoard());
        Position positionTarget = game.getBoard().getPosition(target.get(0), target.get(1), target.get(2));
        ArrayList<Position> affectedPositions = game.getSelectedAction().getShape().getCoveredCordinates(positionTarget);
        ArrayList<StratoShip> army = game.getCurrentPlayer().getArmy().getShips();
        BoardView.showAreaEffect(affectedPositions, game.getBoard(), army);
    }

    /**
     * Esegue l'azione selezionata
     */
    public void executeAction() {
        StratoShip actor = game.getSelectedShip();
        Action action = game.getSelectedAction();
        
        List<Integer> target = InputView.getPositionTarget(game.getBoard());
        Position targetPosition = game.getBoard().getPosition(target.get(0), target.get(1), target.get(2));
        
        if (action.isValidTarget(game.getBoard(), targetPosition, actor)) {
            action.doAction(game.getBoard(), targetPosition, actor);
            //System.out.println("\n✓ Azione eseguita con successo!");
        } else {
            //System.out.println("\n✗ Target non valido per questa azione!");
            executeAction(); // Chiedi di nuovo finché non viene selezionato un target valido
        }
    }
}