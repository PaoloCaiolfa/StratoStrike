package stratostrike.Domain.Model.validate;
import stratostrike.Domain.Model.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ValidateRange.class, name = "validateRange"),
    @JsonSubTypes.Type(value = ValidateLevel.class, name = "validateLevel"),
    @JsonSubTypes.Type(value = ValidatePosition.class, name = "validatePosition"),
    @JsonSubTypes.Type(value = ValidateHigh.class, name = "validateHigh")
    
})

public interface Validate {
    ValidationResult validate(Context context);
} 
