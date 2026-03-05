package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape.Circle;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;
import stratostrike.Domain.Model.validate.ValidationResult;


@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "subType",
    visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OneZoneMove.class, name = "OneZoneMove")
})

public abstract class Movement implements Action {
    protected String name;
    protected String description;
    protected Shape range;
    protected ArrayList<Validate> validators;

    protected Movement() {
    this.validators = new ArrayList<>();
    }

    public Movement(String name, String description, Shape range, ArrayList<Validate> validators) {
        this.name = name;
        this.description = description;
        this.range = range;
        this.validators = validators;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void doAction(Context context) {
        // Default implementation (can be overridden by subclasses)
    }

    // It doesn't use shape in movement
    @Override
    public Shape getShape() { // forma dell'azione
        return null;
    }
    
    @Override
    public Shape getRange() { // area in cui scelgo il taret 
        return range;
    }

    public void setRange(Shape range) {
        this.range = range;
    }

    public ArrayList<Validate> getValidators() {
        return validators;
    }

    public void setValidators(ArrayList<Validate> validators) {
        this.validators = validators;
    }

    @Override
    public ValidationResult isValidTarget(Context context) {
        for (Validate v : validators) {
            ValidationResult result = v.validate(context);
            if (!result.isValid()) {
                return result;
            }
        }
        return new ValidationResult(true, "Target is valid.");
    }


    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("         Descrizione: ").append(description).append("\n");
        if (range != null && range instanceof Circle) {
            Circle circle = (Circle) range;
            details.append("         Raggio: ").append(circle.getRadius());
        }
        return details.toString();
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}
   