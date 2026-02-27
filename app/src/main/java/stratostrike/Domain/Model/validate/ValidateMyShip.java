package stratostrike.Domain.Model.validate;

import stratostrike.Domain.Model.Context;

public class ValidateMyShip implements Validate  {
    
    @Override
    public ValidationResult validate(Context context) {
        if (context.getTargetPosition() == null || context.getTargetPosition().getShip() == null) {
            return new ValidationResult(false, "Non hai selezionato una nave bersaglio.");
        }

        if (context.getSelectedShip().getIdArmy() == context.getTargetPosition().getShip().getIdArmy()) {
            return new ValidationResult(true, "La nave selezionata è della tua armata.");
        } else {
            return new ValidationResult(false, "La nave selezionata non è della tua armata.");
        }
    }
}
