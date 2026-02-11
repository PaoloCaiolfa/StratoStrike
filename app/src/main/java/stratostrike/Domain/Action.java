package stratostrike.Domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "subType",
    visible = true
)
@JsonSubTypes({
        //@JsonSubTypes.Type(value = Capability.class, name = "Capability"),
        //@JsonSubTypes.Type(value = Movement.class, name = "Movement"),
        //@JsonSubTypes.Type(value = SpecialAbility.class, name = "SpecialAbility")
        @JsonSubTypes.Type(value = PointAttack.class, name = "PointAttack"),
        @JsonSubTypes.Type(value = AreaShield.class, name = "AreaShield"),
        @JsonSubTypes.Type(value = OneZoneMove.class, name = "OneZoneMove"),
        @JsonSubTypes.Type(value = DoubleAttack.class, name = "DoubleAttack")
})

public interface Action {

    String getName();

    void doAction(Board board, Position target, StratoShip actor);

    boolean isValidTarget(Board board, Position target, StratoShip actor);

    Shape getShape();

    Action cloneAction();

    String getDetails();
}
