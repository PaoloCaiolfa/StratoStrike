package stratostrike.Domain.Model.Action;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import stratostrike.Domain.Model.Context;
import stratostrike.Domain.Model.Shape;
import stratostrike.Domain.Model.validate.ValidationResult;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "subType",
    visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointAttack.class, name = "PointAttack"),
        @JsonSubTypes.Type(value = AreaShield.class, name = "AreaShield"),
        @JsonSubTypes.Type(value = OneZoneMove.class, name = "OneZoneMove"),
        @JsonSubTypes.Type(value = DoubleAttack.class, name = "DoubleAttack")
})

public interface Action {

    String getName();

    void doAction(Context context);

    ValidationResult isValidTarget(Context context);

    Shape getShape();

    Shape getRange();

    Action cloneAction();

    String getDetails();

    String toString();
}
