package stratostrike.Domain.Model.validate;
import stratostrike.Domain.Model.*;
import stratostrike.Domain.Model.Army.StratoShip;

public class ValidateLevel implements Validate {
    @Override
    public ValidationResult validate(Context context) {
        // Implementa la logica di validazione del livello
        StratoShip selectedShip = context.getSelectedShip();
        int attackerLevel = context.getBoard().getShipPosition(selectedShip).getZ();
        int targetLevel = context.getTargetPosition().getZ();

        if (attackerLevel >= targetLevel) {
            return new ValidationResult(true, "Attacker level is sufficient for this action.");
        } else {
            return new ValidationResult(false, "Attacker level is too low for this action.");
        }
    }   

    
}
