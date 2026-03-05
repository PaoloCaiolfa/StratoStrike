package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.Shape.Circle;
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
    @JsonSubTypes.Type(value = ImproveMovement.class, name = "ImproveMovement")
})

public abstract class SpecialAbility implements Action {
    protected String name;
    protected String description;
    protected Shape shape;
    protected Shape range;
    protected ArrayList<Validate> validators;
    protected ArrayList<Validate> activators;

    protected SpecialAbility() {
        this.validators = new ArrayList<>();
        this.activators = new ArrayList<>();
    }

    public SpecialAbility(String name, String description,Shape range, Shape shape, ArrayList<Validate> validators, ArrayList<Validate> activators) {
        this.name = name;
        this.description = description;
        this.shape = shape;
        this.range = range;
        this.validators = validators;
        this.activators= activators;
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
    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public ArrayList<Validate> getValidators() {
        return validators;
    }   

    public void setValidators(ArrayList<Validate> validators) {
        this.validators = validators;
    }

    public ArrayList<Validate> getActivators() {
        return activators;
    }

    public void setActivators(ArrayList<Validate> activators) {
        this.activators = activators;
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
    public void doAction(Context context) {
        // Default implementation (can be overridden by subclasses)
    }

    @Override
    public Shape getRange() {
        return range;
    }

    public void setRange(Shape range) {
        this.range = range;
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

    public ValidationResult allActivatorsVerified(Context context) {
        for (Validate activator : activators) {
            ValidationResult result = activator.validate(context);
            if (!result.isValid()) {
                return result;
            }
        }
        return new ValidationResult(true, "All activators are valid.");
    }

    public String printActivators() {
        StringBuilder sb = new StringBuilder();
        for (Validate validate : activators) {
            sb.append(validate.getClass().getSimpleName()).append(", ");
        }
        return sb.toString();
    }

}

