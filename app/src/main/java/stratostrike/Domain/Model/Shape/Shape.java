package stratostrike.Domain.Model.Shape;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import stratostrike.Domain.Model.Position;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type" 
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
    @JsonSubTypes.Type(value = Cross.class, name = "Cross"),
    @JsonSubTypes.Type(value = Sphere.class, name = "Sphere")
})

public interface Shape {
    ArrayList<Position> getCoveredCordinates(Position pos);

}