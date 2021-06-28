package exceptions;

/**
 * @Create 6/28/2021
 * Extends of {@link RuntimeException} interface.
 */

public class FetchFailedException extends RuntimeException {
    public FetchFailedException() {

    }

    public FetchFailedException(String message) {
        super(message);
    }

    public FetchFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}