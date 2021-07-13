package services.impl;

import models.Employee;
import repositories.jdbc.impl.EmployeeJDBCRepository;
import services.AbstractService;

import java.util.Set;

/**
 * @Create 6/29/2021
 * @Extends of {@link AbstractService} class.
 */

public class EmployeeService extends AbstractService<Employee>  {

    @Override
    protected EmployeeJDBCRepository jdbcRepository() {
        return new EmployeeJDBCRepository();
    }

    public Set<Employee> getAllByDepartmentId(Long id) {
        return jdbcRepository().getAllByDepartmentId(id);
    }

    public Employee getByEmail(String email) {
        return jdbcRepository().getByEmail(email);
    }

    public boolean existByEmail(String email) {
        return jdbcRepository().existByEmail(email);
    }

    public Long getIdByEmail(String email) {
        return jdbcRepository().getIdByEmail(email);
    }

    public long getEmpId(String queryString) {
        return extractIds(queryString).get(1);
    }
}
