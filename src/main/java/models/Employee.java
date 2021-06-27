package models;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static validation.IEmployeeValidationMessages.*;

/**
 * @Create 6/26/2021
 * Implementation of {@link Serializable} interface.
 */

public class Employee implements Serializable {
    private static final long serialVersionUID = 3968944660015916368L;

    private Long id;

    @NotNull(message = EMPLOYEE_FIRST_NAME_NULL_ERROR_MESSAGE)
    @NotEmpty(message = EMPLOYEE_FIRST_NAME_EMPTY_ERROR_MESSAGE)
    @Size(min = 1, max = 32, message = EMPLOYEE_FIRST_NAME_SIZE_ERROR_MESSAGE)
    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = EMPLOYEE_FIRST_NAME_PATTERN_ERROR_MESSAGE)
    private String firstName;

    @NotNull(message = EMPLOYEE_LAST_NAME_NULL_ERROR_MESSAGE)
    @NotEmpty(message = EMPLOYEE_LAST_NAME_EMPTY_ERROR_MESSAGE)
    @Size(min = 1, max = 32, message = EMPLOYEE_LAST_NAME_SIZE_ERROR_MESSAGE)
    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = EMPLOYEE_LAST_NAME_PATTERN_ERROR_MESSAGE)
    private String lastName;

    @NotNull(message = EMPLOYEE_EMAIL_NULL_ERROR_MESSAGE)
    @NotEmpty(message = EMPLOYEE_EMAIL_EMPTY_ERROR_MESSAGE)
    @Email(message = EMPLOYEE_EMAIL_EMAIL_ERROR_MESSAGE)
    private String email;

    @NotNull(message = EMPLOYEE_SALARY_PER_HOUR_NULL_ERROR_MESSAGE)
    @DecimalMin(value = "1", message = EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MIN_ERROR_MESSAGE)
    @DecimalMax(value = "99999", message = EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MAX_ERROR_MESSAGE)
    private BigDecimal salaryPerHour;

    @NotNull(message = EMPLOYEE_DATE_OF_BIRTH_NOT_NULL_ERROR_MESSAGE)
    @Past(message = EMPLOYEE_DATE_OF_BIRTH_PAST_ERROR_MESSAGE)
    private LocalDate dateOfBirth;

    private boolean isHead;

    private Department department;

    public Employee() {

    }

    public Employee(@NotNull(message = EMPLOYEE_FIRST_NAME_NULL_ERROR_MESSAGE)
                    @NotEmpty(message = EMPLOYEE_FIRST_NAME_EMPTY_ERROR_MESSAGE)
                    @Size(min = 1, max = 32, message = EMPLOYEE_FIRST_NAME_SIZE_ERROR_MESSAGE)
                    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = EMPLOYEE_FIRST_NAME_PATTERN_ERROR_MESSAGE) String firstName,

                    @NotNull(message = EMPLOYEE_LAST_NAME_NULL_ERROR_MESSAGE)
                    @NotEmpty(message = EMPLOYEE_LAST_NAME_EMPTY_ERROR_MESSAGE)
                    @Size(min = 1, max = 32, message = EMPLOYEE_LAST_NAME_SIZE_ERROR_MESSAGE)
                    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = EMPLOYEE_LAST_NAME_PATTERN_ERROR_MESSAGE) String lastName,

                    @NotNull(message = EMPLOYEE_EMAIL_NULL_ERROR_MESSAGE)
                    @NotEmpty(message = EMPLOYEE_EMAIL_EMPTY_ERROR_MESSAGE)
                    @Email(message = EMPLOYEE_EMAIL_EMAIL_ERROR_MESSAGE) String email,

                    @NotNull(message = EMPLOYEE_SALARY_PER_HOUR_NULL_ERROR_MESSAGE)
                    @DecimalMin(value = "1", message = EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MIN_ERROR_MESSAGE)
                    @DecimalMax(value = "99999", message = EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MAX_ERROR_MESSAGE) BigDecimal salaryPerHour,

                    @NotNull(message = EMPLOYEE_DATE_OF_BIRTH_NOT_NULL_ERROR_MESSAGE)
                    @Past(message = EMPLOYEE_DATE_OF_BIRTH_PAST_ERROR_MESSAGE) LocalDate dateOfBirth,
                    boolean isHead) {
        this.firstName = firstName.strip();
        this.lastName = lastName.strip();
        this.email = email.strip();
        this.salaryPerHour = salaryPerHour;
        this.dateOfBirth = dateOfBirth;
        this.isHead = isHead;
    }

    // Additional constructor with ID for JDBC implementation
    public Employee(Long id,

                    @NotNull(message = EMPLOYEE_FIRST_NAME_NULL_ERROR_MESSAGE)
                    @NotEmpty(message = EMPLOYEE_FIRST_NAME_EMPTY_ERROR_MESSAGE)
                    @Size(min = 1, max = 32, message = EMPLOYEE_FIRST_NAME_SIZE_ERROR_MESSAGE)
                    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = EMPLOYEE_FIRST_NAME_PATTERN_ERROR_MESSAGE) String firstName,

                    @NotNull(message = EMPLOYEE_LAST_NAME_NULL_ERROR_MESSAGE)
                    @NotEmpty(message = EMPLOYEE_LAST_NAME_EMPTY_ERROR_MESSAGE)
                    @Size(min = 1, max = 32, message = EMPLOYEE_LAST_NAME_SIZE_ERROR_MESSAGE)
                    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = EMPLOYEE_LAST_NAME_PATTERN_ERROR_MESSAGE) String lastName,

                    @NotNull(message = EMPLOYEE_EMAIL_NULL_ERROR_MESSAGE)
                    @NotEmpty(message = EMPLOYEE_EMAIL_EMPTY_ERROR_MESSAGE)
                    @Email(message = EMPLOYEE_EMAIL_EMAIL_ERROR_MESSAGE) String email,

                    @NotNull(message = EMPLOYEE_SALARY_PER_HOUR_NULL_ERROR_MESSAGE)
                    @DecimalMin(value = "1", message = EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MIN_ERROR_MESSAGE)
                    @DecimalMax(value = "99999", message = EMPLOYEE_SALARY_PER_HOUR_DECIMAL_MAX_ERROR_MESSAGE) BigDecimal salaryPerHour,

                    @NotNull(message = EMPLOYEE_DATE_OF_BIRTH_NOT_NULL_ERROR_MESSAGE)
                    @Past(message = EMPLOYEE_DATE_OF_BIRTH_PAST_ERROR_MESSAGE) LocalDate dateOfBirth,

                    boolean isHead) {
        this.id = id;
        this.firstName = firstName.strip();
        this.lastName = lastName.strip();
        this.email = email.strip();
        this.salaryPerHour = salaryPerHour;
        this.dateOfBirth = dateOfBirth;
        this.isHead = isHead;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.strip();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.strip();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.strip();
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public boolean isHead() {
        return isHead;
    }

    public void setHead(boolean head) {
        isHead = head;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return isHead == employee.isHead &&
                Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(email, employee.email) &&
                Objects.equals(salaryPerHour, employee.salaryPerHour) &&
                Objects.equals(dateOfBirth, employee.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, salaryPerHour, dateOfBirth, isHead);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", salaryPerHour=" + salaryPerHour +
                ", dateOfBirth=" + dateOfBirth +
                ", isHead=" + isHead +
                '}';
    }
}