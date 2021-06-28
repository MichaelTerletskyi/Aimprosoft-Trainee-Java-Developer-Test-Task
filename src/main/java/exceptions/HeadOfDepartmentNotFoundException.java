package exceptions;

/**
 * @Create 6/28/2021
 * Extends of {@link RuntimeException} interface.
 */

public class HeadOfDepartmentNotFoundException extends RuntimeException {
    public HeadOfDepartmentNotFoundException() {

    }

    public HeadOfDepartmentNotFoundException(String message) {
        super(message);
    }

    public HeadOfDepartmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}