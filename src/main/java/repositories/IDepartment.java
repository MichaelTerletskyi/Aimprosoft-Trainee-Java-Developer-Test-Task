package repositories;

import models.Department;

import java.util.Set;

/**
 * @Create 6/27/2021
 */

public interface IDepartment {
    Department getById(Long id, boolean fetchEmployees);

    Set<Department> getAll(boolean fetchEmployees);

    Department getByTitle(String title);

    Department getByTitle(String title, boolean fetchEmployees);

    boolean existByTitle(String title);

    Long getIdByTitle(String title);
}