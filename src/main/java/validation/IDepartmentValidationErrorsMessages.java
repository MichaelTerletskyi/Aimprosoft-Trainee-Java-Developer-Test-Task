package validation;

/**
 * @Create 6/26/2021
 */

public interface IDepartmentValidationErrorsMessages {
    String DEPARTMENT_TITLE_NULL_ERROR_MESSAGE = "Department title cannot be null";
    String DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE = "Department title cannot be empty";
    String DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE = "Department title can contains from 1 to 64 characters";
    String DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE = "Department title can contains only latins or cyrillic letter";

    String DEPARTMENT_DESCRIPTION_SIZE_ERROR_MESSAGE = "Department description can contains to 255 characters";
}