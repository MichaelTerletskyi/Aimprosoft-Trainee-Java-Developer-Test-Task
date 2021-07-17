package repositories.jdbc;

import enums.EmployeeFetchType;
import models.Department;

import java.util.Set;

/**
 * @Create 7/15/2021
 * @Extends of {@link AbstractJDBCRepository} class.
 */

public abstract class AbstractDepartmentJDBCRepository extends AbstractJDBCRepository<Department> {
    public abstract Department getById(Long id, EmployeeFetchType employeeFetchType);
    public abstract Long getIdByTitle(String title);
    public abstract Set<Department> getAll(EmployeeFetchType employeeFetchType);
    public abstract Department getByTitle(String title);
    public abstract Department getByTitle(String title, EmployeeFetchType employeeFetchType);
    public abstract boolean existByTitle(String title);
    public abstract void addEmployee(long departmentId, long employeeId);
}