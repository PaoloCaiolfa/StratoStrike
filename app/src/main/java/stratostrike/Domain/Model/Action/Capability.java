package stratostrike.Domain.Model.Action;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.Army.StratoShip;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "subType" // Il discriminatore
                                                                                                   // di secondo livello
                                                                                                   // nel JSON
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointAttack.class, name = "PointAttack"),
        @JsonSubTypes.Type(value = AreaShield.class, name = "AreaShield")
})

public abstract class Capability implements Action {
    protected String name;
    protected String description;
    protected Shape shape;

    public Capability() {}

    public Capability(String name, String description) {
        this.name = name;
        this.description = description;
        this.shape = null;
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
        // Default implementation (can be overridden by subclasses)
    }

    @Override
    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}