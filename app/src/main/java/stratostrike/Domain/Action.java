package stratostrike.Domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Capability.class, name = "Capability"),
        @JsonSubTypes.Type(value = Movement.class, name = "Movement"),
        @JsonSubTypes.Type(value = SpecialAbility.class, name = "SpecialAbility")
})

public interface Action {

    String getName();

    void doAction(Board board, Position target, StratoShip actor);

    boolean isValidTarget(Board board, Position target, StratoShip actor);

    Shape getShape();
}
