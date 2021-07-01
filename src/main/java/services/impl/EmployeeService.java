package services.impl;

import models.Employee;
import repositories.IEmployee;
import repositories.jdbc.impl.EmployeeJDBCRepository;
import services.AbstractService;

import java.util.Set;

/**
 * @Create 6/29/2021
 */

public class EmployeeService extends AbstractService<Employee> implements IEmployee {

    @Override
    protected EmployeeJDBCRepository jdbcRepository() {
        return new EmployeeJDBCRepository();
    }

    /**
     * {@link IEmployee} block
     */

    @Override
    public Set<Employee> getAllByDepartmentId(Long id) {
        return jdbcRepository().getAllByDepartmentId(id);
    }

    @Override
    public Employee getByEmail(String email) {
        return jdbcRepository().getByEmail(email);
    }

    @Override
    public boolean existByEmail(String email) {
        return jdbcRepository().existByEmail(email);
    }

    @Override
    public Long getIdByEmail(String email) {
        return jdbcRepository().getIdByEmail(email);
    }
}
