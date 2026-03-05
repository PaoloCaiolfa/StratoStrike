package stratostrike.Domain.Model.Action;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape.Shape;
import stratostrike.Domain.Model.validate.Validate;
import stratostrike.Domain.Model.validate.ValidationResult;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "subType" )
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointAttack.class, name = "PointAttack"),
        @JsonSubTypes.Type(value = AreaShield.class, name = "AreaShield"),     
        @JsonSubTypes.Type(value = LaserAttack.class, name = "LaserAttack"),
        @JsonSubTypes.Type(value = DroneAttack.class, name = "DroneAttack")
})

public abstract class Capability implements Action {
    protected String name;
    protected String description;
    protected Shape shape;
    protected Shape range;
    protected ArrayList<Validate> validators;

    public Capability() {
        this.validators = new ArrayList<>();
    }

    public Capability(String name, String description, Shape shape, Shape range, ArrayList<Validate> validets) {
        this.name = name;
        this.description = description;
        this.shape = shape;
        this.range = range;
        this.validators= new ArrayList<>();
        for (Validate v : validets) {
            this.validators.add(v);
        }
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
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public Shape getRange() {
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
    public String toString() {
        return name + ": " + description;
    }
}