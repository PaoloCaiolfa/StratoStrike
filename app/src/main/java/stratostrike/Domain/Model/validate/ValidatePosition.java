package stratostrike.Domain.Model.validate;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Position;

public class ValidatePosition implements Validate {
    @Override
    public ValidationResult validate(Context context){
        Position targetPosition= context.getTargetPosition();
        if (targetPosition.isOccupied()) {
            return new ValidationResult(false, "La posizione è già occupata da una nave.");
        }
        return new ValidationResult(true, "Posizione valida."); 
    }
    
}
