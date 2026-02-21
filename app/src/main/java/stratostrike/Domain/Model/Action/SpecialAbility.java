package stratostrike.Domain.Model.Action;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Board;
import stratostrike.Domain.Model.Circle;
import stratostrike.Domain.Model.Position;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.Army.StratoShip;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "subType",
    visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = DoubleAttack.class, name = "DoubleAttack")
})

public abstract class SpecialAbility implements Action {
    protected String name;
    protected String description;
    protected Shape shape;

    protected SpecialAbility() {}

    public SpecialAbility(String name, String description) {
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
