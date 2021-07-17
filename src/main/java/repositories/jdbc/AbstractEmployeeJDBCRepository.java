package repositories.jdbc;

import models.Employee;

import java.util.Set;

/**
 * @Create 6/27/2021
 * @Extends of {@link AbstractJDBCRepository} class.
 */

public abstract class AbstractEmployeeJDBCRepository extends AbstractJDBCRepository<Employee> {
    public abstract Set<Employee> getAllByDepartmentId(Long id);
    public abstract Employee getByEmail(String email);
    public abstract boolean existByEmail(String email);
    public abstract Long getIdByEmail(String email);
}