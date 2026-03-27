package stratostrike.Controller;

import java.util.ArrayList;
import java.util.List;

import stratostrike.Domain.Model.Action.Action;
import stratostrike.Domain.Model.Action.SpecialAbility;
import stratostrike.Domain.Model.Army.StratoShip;
import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Observer;
import stratostrike.Domain.Model.Player;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.StratoCraftGame;
import stratostrike.Domain.Model.ViewData;
import stratostrike.Domain.Model.validate.ValidationResult;
import stratostrike.GameEvent;

public class MakeTurn implements EventSource {

    private StratoCraftGame game;
    private ViewData viewData;

    private List<Observer> observers = new ArrayList<>();
   
    public MakeTurn(StratoCraftGame game) {
        this.game = game;    
        this.viewData = new ViewData();
    }

    /** ========== SETUP =========== */

    /**
     * Aggiunge un observer alla lista degli observer, gli observer sono i componenti della view che si registrano per essere notificati ogni volta che lo stato del gioco cambia, in modo da poter aggiornare la view di conseguenza
     * @param observer l'observer da aggiungere alla lista degli observer
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Notifica tutti gli observer di un cambio nello stato del gioco, questo metodo dovrebbe essere chiamato ogni volta che lo stato del gioco cambia per assicurare che la view sia sempre aggiornata con lo stato corrente del gioco
     */
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }


    /** 
     * Aggiorna i dati della view con le informazioni correnti del gioco, questo metodo viene chiamato prima di notificare gli observer per assicurare che la view abbia sempre i dati più aggiornati quando viene aggiornata dagli observer
    */
    public void updateViewData() {
        viewData.from(game);
    }  

    /** 
     * Aggiorna la view chiamando il metodo per aggiornare i dati della view e notificando gli observer, questo metodo dovrebbe essere chiamato ogni volta che lo stato del gioco cambia per assicurare che la view sia sempre aggiornata con lo stato corrente del gioco
    */
    public void refresh() {
        updateViewData();
        notifyObservers();
    }

    /**
     * Inizia il turno del giocatore corrente, questo metodo viene chiamato all'inizio di ogni turno per resettare lo stato del contesto di gioco e aggiornare la view con le informazioni correnti del gioco. Inoltre, imposta l'evento corrente su SELECT_SHIP per iniziare la fase di selezione della nave da parte del giocatore.
     */
    public void playTurn() {
        game.getContext().resetForNewTurn();
        game.getContext().incrementTurnNumber();
        refresh();
        game.setCurrentEvent(GameEvent.SELECT_SHIP);
        refresh();
    }

    /** ========== GETTERS ========== */

    /**
     * Restituisce la board del gioco
     * @return la board del gioco
     */
    public Board getBoard() {
        return game.getBoard();
    }

    /**
     * Restituisce l'evento corrente del gioco
     * @return l'evento corrente del gioco
     */
    @Override
    public GameEvent getCurrentEvent() {
        return game.getCurrentEvent();
    }

    /**
     * Restituisce il gioco
     * @return il gioco
     */
    public StratoCraftGame getGame() {
        return game;
    }

    /**
     * Restituisce i dati della view
     * @return i dati della view
     */
    public ViewData getViewData() {
        return viewData;
    }


    /** ========== EVENT HANDLING ========== */

    /**
     * Termina il turno corrente, settando l'evento corrispondente
     */
    public void endTurn() {
        game.setCurrentEvent(GameEvent.TURN_ENDED);
        refresh();
    }

    /**
     * Seleziona una nave dal player corrente
     * @param selectedIndex l'indice della nave selezionata
     */
    public void selectShip(int selectedIndex) {
        if (selectedIndex == game.getContext().getCurrentPlayer().getArmy().getAliveShips().size()) {
            game.setCurrentEvent(GameEvent.TURN_ENDED);
        }
        else{
            Player current = game.getContext().getCurrentPlayer();
            game.getContext().setSelectedShip(current.getArmy().getAliveShips().get(selectedIndex));
            game.setCurrentEvent(GameEvent.SELECT_ACTION);
        }
      
        refresh();
    }

    /**
     * Seleziona un'azione dalla nave selezionata
     * @param selectedIndex l'indice dell'azione selezionata
     */
    public void selectAction(int selectedIndex) {

        if (selectedIndex == game.getContext().getSelectedShip().getActions().size()) {
            game.setCurrentEvent(GameEvent.SELECT_SHIP);
        }
        else{
            StratoShip selectedShip = game.getContext().getSelectedShip();  
            Action selectedAction = selectedShip.getActions().get(selectedIndex);
            game.getContext().setSelectedAction(selectedAction);
            if (selectedAction instanceof SpecialAbility) {
             game.setCurrentEvent(GameEvent.SPECIAL_ACTION_SELECTED);
            }
            else{
             game.setCurrentEvent(GameEvent.SELECT_POSITION);
            Position shipPosition = game.getBoard().getShipPosition(selectedShip);
            ArrayList<Position> affectedPositions = game.getContext().getSelectedAction().getRange().getCoveredCordinates(shipPosition);
            game.getContext().setAreaEffect(affectedPositions);

            //show range for the selection of target 
            
            }

        }
        refresh();
    }


    /**
     * Seleziona una posizione bersaglio
     * @param target le coordinate della posizione bersaglio
     */
    public void selectTarget(ArrayList<Integer> target) {
        Position positionTarget = game.getBoard().getPositionByCoordinates(target);
        game.getContext().setTargetPosition(positionTarget);
        game.setCurrentEvent(GameEvent.EXECUTE_ACTION);
        if (game.getContext().getSelectedAction().getShape() != null) {
            //show area of effect for the selection of target
            ArrayList<Position> affectedPositions = game.getContext().getSelectedAction().getShape().getCoveredCordinates(positionTarget);
            game.getContext().setAreaEffect(affectedPositions);
        }    
        refresh();
    }


    /**
     * Esegue l'azione selezionata
     * @param confirmation
     */
    public void executeAction(boolean confirmation) {

        Action action = game.getContext().getSelectedAction();

        // Confirmation si riferisce alla conferma da parte del giocatore di voler effettuare l'azione
        if (confirmation) {

            ValidationResult result = action.isValidTarget(game.getContext());

            // Il secondo controllo viene fatti sui validation assegnati alla specifica azione, se almeno uno non è verificato, allora l'azione non può essere effettuata
            if (result.isValid()) {

                // l'ultimo controllo viene eseguito sulla tipologia di azione (Capability, Movement, SpecialAbility) per verificare che non siano già state effettuate azioni di quella tipologia in questo turno, in caso affermativo, viene mostrato un messaggio di errore e si torna alla selezione dell'azione
                if (game.getContext().actionAlreadyDone(action)) {
                    viewData.setErrorMessage("Hai già usato un'azione di questo tipo in questo turno, seleziona un'altra azione o termina il turno.");
                    game.setCurrentEvent(GameEvent.SELECT_ACTION);
                }
                else{
                    game.getContext().setActionControl(action);
                    action.doAction(game.getContext());
                    game.setCurrentEvent(GameEvent.SELECT_SHIP);
                    
                    if (game.getContext().allActionsDone()) {
                        game.setCurrentEvent(GameEvent.TURN_ENDED);
                    }
                }
                
            } else {
                viewData.setErrorMessage(result.errorMessage());
                game.setCurrentEvent(GameEvent.SELECT_ACTION);
            }
        } else {
            game.setCurrentEvent(GameEvent.SELECT_ACTION);
        }

        refresh();
    }

    /**
     * Mostra i dettagli per l'azione speciale selezionata, se l'azione speciale non ha un range, viene eseguita direttamente, altrimenti si passa alla selezione della posizione bersaglio come per le altre azioni
     */
    public void detailsForSpecialActionSelected() {

        SpecialAbility action = (SpecialAbility) game.getContext().getSelectedAction();
        if (action.getRange() == null) {
            ValidationResult result = action.allActivatorsVerified(game.getContext());
            if (result.isValid()) {
                action.doAction(game.getContext());
                
            }else {
                viewData.setErrorMessage(result.errorMessage());
            }
            game.setCurrentEvent(GameEvent.SELECT_SHIP);

            // Se l'azione speciale è stata eseguita con successo, viene verificato se sono state effettuate tutte le azioni disponibili per il turno, in caso affermativo, si termina il turno, altrimenti si torna alla selezione della nave
            if (game.getContext().allActionsDone()) {
                game.setCurrentEvent(GameEvent.TURN_ENDED);
            }
            else{
                game.setCurrentEvent(GameEvent.SELECT_SHIP);
            }
        }else{
            game.setCurrentEvent(GameEvent.SELECT_POSITION);
        }
    
        refresh();
    }

}