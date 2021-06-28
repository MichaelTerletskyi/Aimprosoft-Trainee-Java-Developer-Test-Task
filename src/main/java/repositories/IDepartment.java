package repositories;

import models.Department;

/**
 * @Create 6/27/2021
 */

public interface IDepartment {
    Department getById(Long id, boolean fetchEmployees);

    Department getByTitle(String title);

    Department getByTitle(String title, boolean fetchEmployees);

    boolean existByTitle(String title);

    Long getIdByTitle(String title);
}