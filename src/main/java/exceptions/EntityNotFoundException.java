package exceptions;

/**
 * @Create 6/27/2021
 * Extends of {@link RuntimeException} interface.
 */

public class EntityNotFoundException extends RuntimeException  {
    public EntityNotFoundException() {

    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}