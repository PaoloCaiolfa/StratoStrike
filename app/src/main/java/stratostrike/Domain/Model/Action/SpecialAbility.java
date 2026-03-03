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
})

public abstract class SpecialAbility implements Action {
    protected String name;
    protected String description;
    protected Shape shape;
    protected ArrayList<Validate> validators;

    protected SpecialAbility() {
        this.validators = new ArrayList<>();
    }

    public SpecialAbility(String name, String description) {
        this.name = name;
        this.description = description;
        this.shape = null;
        this.validators = new ArrayList<>();
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
        return shape;
    }

    public void setRange(Shape range) {
        this.shape = range;
    }

    @Override
    public String getDetails() {
        StringBuilder details = new StringBuilder();
        details.append("         Descrizione: ").append(description).append("\n");
        if (shape != null && shape instanceof Circle) {
            Circle circle = (Circle) shape;
            details.append("         Raggio: ").append(circle.getRadius());
        }
        return details.toString();
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}
