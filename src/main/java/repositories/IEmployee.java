package repositories;

import models.Employee;

import java.util.Set;

/**
 * @Create 6/27/2021
 */

public interface IEmployee {
    Set<Employee> getAllByDepartmentId(Long id);

    Employee getByEmail(String email);

    boolean existByEmail(String email);

    Long getIdByEmail(String email);
}