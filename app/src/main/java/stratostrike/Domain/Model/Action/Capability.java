package stratostrike.Domain.Model.Action;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.Army.StratoShip;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "subType" )
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointAttack.class, name = "PointAttack"),
        @JsonSubTypes.Type(value = AreaShield.class, name = "AreaShield")
})

public abstract class Capability implements Action {
    protected String name;
    protected String description;
    protected Shape shape;
    protected Shape range;
    protected ArrayList<String> conditions;

    public Capability() {}

    public Capability(String name, String description, Shape shape, Shape range) {
        this.name = name;
        this.description = description;
        this.shape = shape;
        this.range = range;
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
    public void doAction(Board board, Position target, StratoShip actor) {
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Shape getRange() {
        return range;
    }

    public void setRange(Shape range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return name + ": " + description;
    }
}