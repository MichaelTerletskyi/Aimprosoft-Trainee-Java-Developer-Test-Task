package repositories;

import models.Department;

/**
 * @Create 6/27/2021
 */

public interface IDepartment {
    Department getById(Long id, boolean includeEmployees);

    Department getByTitle(String title);

    Department getByTitle(String title, boolean includeEmployees);

    boolean existByTitle(String title);

    Long getIdByTitle(String title);
}