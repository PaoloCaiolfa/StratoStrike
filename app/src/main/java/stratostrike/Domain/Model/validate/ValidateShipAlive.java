package stratostrike.Domain.Model.validate;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Position;

public class ValidateShipAlive implements Validate {

    @Override
    public ValidationResult validate(Context context) {
        Position targetPos = context.getTargetPosition();
        if (targetPos.isOccupied()) {
            if (targetPos.getShip().isDestroyed()) {
                return new ValidationResult(false, "La nave è già distrutta.");
            } else {
                return new ValidationResult(true, "La nave è ancora viva.");
            }
        } else {
            return new ValidationResult(false, "Non c'è nessuna nave in questa posizione.");
        }
    }
}