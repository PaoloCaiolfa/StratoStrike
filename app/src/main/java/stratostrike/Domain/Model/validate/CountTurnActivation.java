package stratostrike.Domain.Model.validate;
import stratostrike.Domain.Model.Context;
import stratostrike.Settings;

public class CountTurnActivation implements Validate {
    
    @Override 
    public ValidationResult validate(Context context) {
        if (context.getTurnNumber() >= Settings.SPECIAL_ABILITY_ACTIVATION) {
            return new ValidationResult(true, "Special ability can be activated.");
        } else {

            return new ValidationResult(false, "Special ability is not valid in first turns.");
        }
    }
    

    
}
