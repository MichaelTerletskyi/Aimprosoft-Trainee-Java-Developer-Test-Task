package entityTests;

import models.Department;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

import static validation.IDepartmentValidationErrorsMessages.*;

/**
 * @Create 6/26/2021
 */

class DepartmentEntityTest {
    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private Validator validator = validatorFactory.getValidator();

    @Test
    void departmentTitleWithLatinLetters() {
        Department department = new Department("Department");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void departmentTitleWithTwoEnglishWords() {
        Department department = new Department("Financial Department");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void departmentTitleWithCyrillicLetters() {
        Department department = new Department("Департамент");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void departmentTitleWithTwoRussianWords() {
        Department department = new Department("Департамент Образования");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    void departmentTitleWithNumbers() {
        Department department = new Department("1234");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        Assertions.assertEquals(1, violations.size());

        for (ConstraintViolation violation : violations) {
            Assertions.assertEquals(violation.getMessage(), DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE);
        }
    }

    @Test
    void departmentWithEmptyTitle() {
        Department department = new Department("");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(2, violations.size());
        Assertions.assertTrue(violationMessages.contains(DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE));
    }

    @Test
    void departmentTitleWithWhiteSpace() {
        Department department = new Department("         ");
        Set<ConstraintViolation<Department>> violations = validator.validate(department);

        Set<String> violationMessages = new HashSet<>();
        violations.forEach(v -> violationMessages.add(v.getMessage()));

        Assertions.assertEquals(2, violations.size());
        Assertions.assertTrue(violationMessages.contains(DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE));
        Assertions.assertTrue(violationMessages.contains(DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE));
    }
}