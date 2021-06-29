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
    protected DepartmentJDBCRepository repository() {
        return new DepartmentJDBCRepository();
    }

    /**
     * {@link IDepartment} block
     */

    @Override
    public Department getById(Long id, boolean fetchEmployees) {
        return repository().getById(id, fetchEmployees);
    }

    @Override
    public Department getByTitle(String title) {
        return repository().getByTitle(title);
    }

    @Override
    public Department getByTitle(String title, boolean fetchEmployees) {
        return repository().getByTitle(title, fetchEmployees);
    }

    @Override
    public boolean existByTitle(String title) {
        return repository().existByTitle(title);
    }

    @Override
    public Long getIdByTitle(String title) {
        return repository().getIdByTitle(title);
    }

    /**
     * Overload block
     */

    public void addEmployee(Department department, Employee employee) {
        repository().addEmployee(department.getId(), employee.getId());
    }

    public void addEmployee(Department department, Long employeeId) {
        repository().addEmployee(department.getId(), employeeId);
    }

    public void addEmployee(Long departmentId, Employee employee) {
        repository().addEmployee(departmentId, employee.getId());
    }

    public void addEmployee(Long departmentId, Long employeeId) {
        repository().addEmployee(departmentId, employeeId);
    }

    public void addEmployee(Department department, Set<Employee> employees) {
        employees.forEach(employee -> repository().addEmployee(department.getId(), employee.getId()));
    }

    public void addEmployee(Long departmentId, Set<Employee> employees) {
        employees.forEach(employee -> repository().addEmployee(departmentId, employee.getId()));
    }
}