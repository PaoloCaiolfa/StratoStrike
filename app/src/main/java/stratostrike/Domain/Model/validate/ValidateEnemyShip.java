package stratostrike.Domain.Model.validate;

import stratostrike.Domain.Model.Context;

public class ValidateEnemyShip implements Validate {
    
    @Override
    public ValidationResult validate(Context context) {
        if (context.getTargetPosition() == null || context.getTargetPosition().getShip() == null) {
            return new ValidationResult(false, "Nessuna nave presente nella posizione selezionata.");
        }

        int myArmyId = context.getSelectedShip().getIdArmy();
        int targetArmyId = context.getTargetPosition().getShip().getIdArmy();

        if (myArmyId == targetArmyId) {
            return new ValidationResult(false, "Non puoi colpire una nave alleata");
        }

        return new ValidationResult(true, "Nave nemica rilevata: impatto autorizzato.");
    }
}
