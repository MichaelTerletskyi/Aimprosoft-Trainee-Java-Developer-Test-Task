package services.impl;

import models.Department;
import models.Employee;
import repositories.jdbc.impl.EmployeeJDBCRepository;
import services.AbstractService;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

/**
 * @Create 6/29/2021
 * @Extends of {@link AbstractService} class.
 */

public class EmployeeService extends AbstractService<Employee> {
    private final DepartmentService departmentService = new DepartmentService();

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

    public void attributesSets(HttpServletRequest req) {
        Department department = departmentService.getById(departmentService.getDepId(req.getQueryString()));
        req.setAttribute("departmentId", department.getId());
        req.setAttribute("departmentTitle", department.getTitle());
        req.setAttribute("firstName", req.getParameter("firstName"));
        req.setAttribute("lastName", req.getParameter("lastName"));
        req.setAttribute("email", req.getParameter("email"));
        req.setAttribute("salaryPerHour", BigDecimal.valueOf(Long.parseLong(req.getParameter("salaryPerHour"))));
        req.setAttribute("dateOfBirth", LocalDate.parse(req.getParameter("dateOfBirth")));
        req.setAttribute("head", Boolean.parseBoolean(req.getParameter("head")));
    }

    public void attributesSetsWithId(HttpServletRequest req) {
        Department department = departmentService.getById(departmentService.getDepId(req.getQueryString()));
        Employee employee = super.getById(getEmpId(req.getQueryString()));
        req.setAttribute("departmentId", department.getId());
        req.setAttribute("departmentTitle", department.getTitle());

        req.setAttribute("employeeId", employee.getId());
        req.setAttribute("firstName", employee.getFirstName());
        req.setAttribute("lastName", employee.getLastName());
        req.setAttribute("email", employee.getEmail());
        req.setAttribute("salaryPerHour", employee.getSalaryPerHour());
        req.setAttribute("dateOfBirth", employee.getDateOfBirth());
        req.setAttribute("head", employee.getHead());
    }
}