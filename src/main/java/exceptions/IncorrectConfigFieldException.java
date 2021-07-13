package exceptions;

/**
 * @Create 7/13/2021
 * Extends of {@link RuntimeException} interface.
 */

public class IncorrectConfigFieldException extends RuntimeException {
    public IncorrectConfigFieldException() {

    }

    public IncorrectConfigFieldException(String message) {
        super(message);
    }
}
