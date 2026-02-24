package stratostrike.Domain.Model.validate;

public record ValidationResult(boolean isValid, String errorMessage) {

    public static ValidationResult ok() {
        return new ValidationResult(true, "");
    }

    public static ValidationResult fail(String errorMessage) {
        return new ValidationResult(false, errorMessage);
    }
}