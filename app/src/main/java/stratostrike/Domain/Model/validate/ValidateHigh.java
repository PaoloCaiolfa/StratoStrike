package stratostrike.Domain.Model.validate;

import stratostrike.Domain.Model.*;



public class ValidateHigh implements Validate {

    @Override
    public ValidationResult validate(Context context) {
        // Implementa la logica di validazione per l'altezza
        int targetLevel = context.getTargetPosition().getZ();
        int positionLevel = context.getBoard().getShipPosition(context.getSelectedShip()).getZ();
        if (positionLevel == targetLevel){
            return new ValidationResult(true, "Target is at the same level.");
        
        }
        else return new ValidationResult(false, "Target is at a different level.");
    }
}
