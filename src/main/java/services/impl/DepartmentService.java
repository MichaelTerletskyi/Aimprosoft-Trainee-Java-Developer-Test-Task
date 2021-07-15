package services.impl;

import enums.EmployeeFetchType;
import models.Department;
import models.Employee;
import repositories.jdbc.impl.DepartmentJDBCRepository;
import services.AbstractService;

import java.util.Set;

/**
 * @Create 6/29/2021
 * @Extends of {@link AbstractService} class.
 */

public class DepartmentService extends AbstractService<Department> {

    @Override
    protected DepartmentJDBCRepository jdbcRepository() {
        return new DepartmentJDBCRepository();
    }

    public Department getById(Long id, EmployeeFetchType employeeFetchType) {
        return jdbcRepository().getById(id, employeeFetchType);
    }

    public Set<Department> getAll(EmployeeFetchType employeeFetchType) {
        return jdbcRepository().getAll(employeeFetchType);
    }

    public Department getByTitle(String title) {
        return jdbcRepository().getByTitle(title);
    }

    public Department getByTitle(String title, EmployeeFetchType employeeFetchType) {
        return jdbcRepository().getByTitle(title, employeeFetchType);
    }

    public boolean existByTitle(String title) {
        return jdbcRepository().existByTitle(title);
    }

    public Long getIdByTitle(String title) {
        return jdbcRepository().getIdByTitle(title);
    }

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

    public long extractId(String queryString) {
        return Long.parseLong(queryString.replaceAll("[^0-9]", ""));
    }

    public long getDepId(String queryString) {
        return extractIds(queryString).get(0);
    }
}