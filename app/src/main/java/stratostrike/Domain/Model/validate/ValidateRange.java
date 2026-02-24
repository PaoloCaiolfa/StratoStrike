package stratostrike.Domain.Model.validate;
import stratostrike.Domain.Model.*;
import java.util.ArrayList;
import stratostrike.Domain.Model.Army.StratoShip;




public class ValidateRange implements Validate {

    @Override
    public ValidationResult validate (Context context) {
        // Implementa la logica di validazione del range
        StratoShip selectedShip = context.getSelectedShip();
        Position attackerPos= context.getBoard().getShipPosition(selectedShip);
        ArrayList<Position> validPositions = context.getSelectedAction().getRange().getCoveredCordinates(attackerPos);
        if (validPositions.contains(context.getTargetPosition())) {
            return new ValidationResult(true, "Target is within range.");
        } else {
            return new ValidationResult(false, "Target is out of range.");
        }

    
    }

  
    
}
