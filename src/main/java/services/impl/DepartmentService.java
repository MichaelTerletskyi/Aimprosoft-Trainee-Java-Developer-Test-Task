package services.impl;

import models.Department;
import models.Employee;
import repositories.IDepartment;
import repositories.jdbc.impl.DepartmentJDBCRepository;
import services.AbstractService;

import java.util.Set;

/**
 * @Create 6/29/2021
 */

public class DepartmentService extends AbstractService<Department> implements IDepartment {

    @Override
    protected DepartmentJDBCRepository jdbcRepository() {
        return new DepartmentJDBCRepository();
    }

    /**
     * {@link IDepartment} block
     */

    @Override
    public Department getById(Long id, boolean fetchEmployees) {
        return jdbcRepository().getById(id, fetchEmployees);
    }

    @Override
    public Set<Department> getAll(boolean fetchEmployees) {
        return jdbcRepository().getAll(fetchEmployees);
    }

    @Override
    public Department getByTitle(String title) {
        return jdbcRepository().getByTitle(title);
    }

    @Override
    public Department getByTitle(String title, boolean fetchEmployees) {
        return jdbcRepository().getByTitle(title, fetchEmployees);
    }

    @Override
    public boolean existByTitle(String title) {
        return jdbcRepository().existByTitle(title);
    }

    @Override
    public Long getIdByTitle(String title) {
        return jdbcRepository().getIdByTitle(title);
    }

    /**
     * Overload block
     */

    public void addEmployee(Department department, Employee employee) {
        jdbcRepository().addEmployee(department.getId(), employee.getId());
    }

    public void addEmployee(Department department, Long employeeId) {
        jdbcRepository().addEmployee(department.getId(), employeeId);
    }

    public void addEmployee(Long departmentId, Employee employee) {
        jdbcRepository().addEmployee(departmentId, employee.getId());
    }

    public void addEmployee(Long departmentId, Long employeeId) {
        jdbcRepository().addEmployee(departmentId, employeeId);
    }

    public void addEmployee(Department department, Set<Employee> employees) {
        employees.forEach(employee -> jdbcRepository().addEmployee(department.getId(), employee.getId()));
    }

    public void addEmployee(Long departmentId, Set<Employee> employees) {
        employees.forEach(employee -> jdbcRepository().addEmployee(departmentId, employee.getId()));
    }
}