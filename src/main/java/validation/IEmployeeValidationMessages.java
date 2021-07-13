package validation;

public interface IEmployeeValidationMessages {
    String EMPLOYEE_FIRST_NAME_NULL_ERROR_MESSAGE = "Employee First Name cannot be null";
    String EMPLOYEE_FIRST_NAME_EMPTY_ERROR_MESSAGE = "Employee First Name cannot be empty";
    String EMPLOYEE_FIRST_NAME_SIZE_ERROR_MESSAGE = "Employee First Name can contains from 1 to 32 letters";
    String EMPLOYEE_FIRST_NAME_PATTERN_ERROR_MESSAGE = "Employee First Name can contains only latins or cyrillic letter";

    String EMPLOYEE_LAST_NAME_NULL_ERROR_MESSAGE = "Employee Last Name cannot be null";
    String EMPLOYEE_LAST_NAME_EMPTY_ERROR_MESSAGE = "Employee Last Name cannot be empty";
    String EMPLOYEE_LAST_NAME_SIZE_ERROR_MESSAGE = "Employee Last Name can contains from 1 to 32 letters";
    String EMPLOYEE_LAST_NAME_PATTERN_ERROR_MESSAGE = "Employee Last Name can contains only latins or cyrillic letter";

    String EMPLOYEE_EMAIL_UNIQUE_ERROR_MESSAGE = "Employee with this email is already exist";
    String EMPLOYEE_EMAIL_NULL_ERROR_MESSAGE = "Employee Email cannot be null";
    String EMPLOYEE_EMAIL_EMPTY_ERROR_MESSAGE = "Employee Email cannot be empty";
    String EMPLOYEE_EMAIL_EMAIL_ERROR_MESSAGE = "Incorrect Email";

    String EMPLOYEE_SALARY_PER_HOUR_NULL_ERROR_MESSAGE = "Employee Salary Per Hour cannot be null";
    String EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MIN_ERROR_MESSAGE = "Employee Salary Per Hour cannot be less then 1";
    String EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MAX_ERROR_MESSAGE = "Employee Salary Per Hour cannot be more then 99999";

    String EMPLOYEE_DATE_OF_BIRTH_NOT_NULL_ERROR_MESSAGE = "Employee Date Of Birth cannot be null";
    String EMPLOYEE_DATE_OF_BIRTH_PAST_ERROR_MESSAGE = "Employee Date Of Birth can be only in past";
}