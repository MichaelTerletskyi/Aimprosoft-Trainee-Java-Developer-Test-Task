package models;

import exceptions.FetchFailedException;
import exceptions.HeadOfDepartmentNotFoundException;

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

    @Size(max = 255, message = DEPARTMENT_DESCRIPTION_SIZE_ERROR_MESSAGE)
    private String description;

    private Set<Employee> employees;

    public Department() {

    }

    public Department(@NotNull(message = DEPARTMENT_TITLE_NULL_ERROR_MESSAGE)
                      @NotEmpty(message = DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE)
                      @Size(min = 1, max = 64, message = DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE)
                      @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE) String title) {
        this.title = title.strip();
    }

    public Department(@NotNull(message = DEPARTMENT_TITLE_NULL_ERROR_MESSAGE)
                      @NotEmpty(message = DEPARTMENT_TITLE_EMPTY_ERROR_MESSAGE)
                      @Size(min = 1, max = 64, message = DEPARTMENT_TITLE_SIZE_ERROR_MESSAGE)
                      @Pattern(regexp = "[a-zA-Zа-яА-Я ]*", message = DEPARTMENT_TITLE_PATTERN_ERROR_MESSAGE) String title,
                      @Size(max = 255, message = DEPARTMENT_DESCRIPTION_SIZE_ERROR_MESSAGE) String description) {
        this.title = title;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Employee getHead() {
        if (Objects.isNull(getEmployees())) {
            throw new FetchFailedException("Employees fetch has been failed, try to repeat attempt with fetchEmployees = true");
        }
        return getEmployees().stream().filter(Employee::isHead).findFirst().orElseThrow(()
                -> new HeadOfDepartmentNotFoundException("This department doesn't have Head"));
    }

    public String getFullNameOfHead() {
        try {
            if (!Objects.isNull(getHead())) return String.format("%s %s", getHead().getFirstName(), getHead().getLastName());
        } catch (HeadOfDepartmentNotFoundException e) {
            e.printStackTrace();
        }
        return "No Head";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}