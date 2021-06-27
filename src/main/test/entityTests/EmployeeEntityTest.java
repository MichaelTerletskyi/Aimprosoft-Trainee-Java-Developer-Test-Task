package entityTests;

import models.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static validation.IEmployeeValidationMessages.*;

/**
 * @Create 6/26/2021
 */

class EmployeeEntityTest {
    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private Validator validator = validatorFactory.getValidator();

    @Test
    void employeeWithCorrectFields() {
        Employee employee = new Employee("Dorian", "Grey", "doriangrey@gmail.com",
                BigDecimal.valueOf(100L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void employeeWithCorrectFieldsOnRussin() {
        Employee employee = new Employee("Дориан", "Грей", "doriangrey@gmail.com",
                BigDecimal.valueOf(100L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void employeeWithIncorrectFirstNameWhichContainsDigits() {
        Employee employee = new Employee("Dorian123", "Grey", "doriangrey@gmail.com",
                BigDecimal.valueOf(100L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_FIRST_NAME_PATTERN_ERROR_MESSAGE));
    }

    @Test
    void employeeWithIncorrectLastNameWhichContainsWhiteSpace() {
        Employee employee = new Employee("Dorian", "         ", "doriangrey@gmail.com",
                BigDecimal.valueOf(100L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(2, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_LAST_NAME_EMPTY_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_LAST_NAME_SIZE_ERROR_MESSAGE));
    }

    @Test
    void employeeWithIncorrectEmail() {
        Employee employee = new Employee("Dorian", "Grey", "doriangrey",
                BigDecimal.valueOf(100L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_EMAIL_EMAIL_ERROR_MESSAGE));
    }

    @Test
    void employeeWithEmptyEmail() {
        Employee employee = new Employee("Dorian", "Grey", "",
                BigDecimal.valueOf(100L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_EMAIL_EMPTY_ERROR_MESSAGE));
    }

    @Test
    void employeeWithNegativeSalaryPerHour() {
        Employee employee = new Employee("Dorian", "Grey", "doriangrey@gmail.com",
                BigDecimal.valueOf(-2L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MIN_ERROR_MESSAGE));
    }

    @Test
    void employeeWithSalaryPerHourWhichBiggerThenMaxValue() {
        Employee employee = new Employee("Dorian", "Grey", "doriangrey@gmail.com",
                BigDecimal.valueOf(99999999999L), LocalDate.of(1977, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MAX_ERROR_MESSAGE));
    }


    @Test
    void employeeWithDateOfBirthInFuture() {
        Employee employee = new Employee("Dorian", "Grey", "doriangrey@gmail.com",
                BigDecimal.valueOf(100L), LocalDate.of(2077, 7, 12), true);

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(1, violations.size());
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_DATE_OF_BIRTH_PAST_ERROR_MESSAGE));
    }

    @Test
    void employeeWithNullFields() {
        Employee employee = new Employee();

        Set<ConstraintViolation<Employee>> violations = validator.validate(employee);
        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(8, violations.size());

        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_FIRST_NAME_NULL_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_FIRST_NAME_EMPTY_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_SALARY_PER_HOUR_NULL_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_EMAIL_EMPTY_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_LAST_NAME_EMPTY_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_DATE_OF_BIRTH_NOT_NULL_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_EMAIL_NULL_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(EMPLOYEE_LAST_NAME_NULL_ERROR_MESSAGE));
    }
}