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
    protected EmployeeJDBCRepository repository() {
        return new EmployeeJDBCRepository();
    }

    /**
     * {@link IEmployee} block
     */

    @Override
    public Set<Employee> getAllByDepartmentId(Long id) {
        return repository().getAllByDepartmentId(id);
    }

    @Override
    public Employee getByEmail(String email) {
        return repository().getByEmail(email);
    }

    @Override
    public boolean existByEmail(String email) {
        return repository().existByEmail(email);
    }

    @Override
    public Long getIdByEmail(String email) {
        return repository().getIdByEmail(email);
    }
}
