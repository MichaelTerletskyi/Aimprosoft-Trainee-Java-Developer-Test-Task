package exceptions;

import java.util.Map;

/**
 * @Create 7/01/2021
 * @Extends of {@link RuntimeException} class.
 */

public class ValidationException extends RuntimeException {
    private Map<String, String> errors;

    public Map<String, String> getErrors() {
        return errors;
    }

    public ValidationException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }
}