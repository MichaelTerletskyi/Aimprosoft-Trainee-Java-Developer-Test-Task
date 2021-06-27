package models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import static validation.IDepartmentValidationErrorsMessages.*;

/**
 * @Create 6/26/2021
 * Implementation of {@link Serializable} interface.
 */

public class Department implements Serializable {
    private static final long serialVersionUID = 4045259830246062290L;

    private Long id;

    @NotNull(message = DEPARTMENT_TITLE_NULL_ERROR_MESSAGE)
    @NotEmpty(message = DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE)
    @Size(min = 1, max = 64, message = DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE)
    @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE)
    private String title;

    private Set<Employee> employees;

    public Department() {

    }

    public Department(@NotNull(message = DEPARTMENT_TITLE_NULL_ERROR_MESSAGE)
                      @NotEmpty(message = DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE)
                      @Size(min = 1, max = 64, message = DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE)
                      @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE) String title) {
        this.title = title.strip();
    }

    // Additional constructor with ID for JDBC implementation
    public Department(Long id,
                      @NotNull(message = DEPARTMENT_TITLE_NULL_ERROR_MESSAGE)
                      @NotEmpty(message = DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE)
                      @Size(min = 1, max = 64, message = DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE)
                      @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE) String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.strip();
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}