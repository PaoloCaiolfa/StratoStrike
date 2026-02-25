package stratostrike.Domain.Model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type" 
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = Circle.class, name = "Circle"),
    @JsonSubTypes.Type(value = Cross.class, name = "Cross")
})

public interface Shape {
    ArrayList<Position> getCoveredCordinates(Position pos);

}