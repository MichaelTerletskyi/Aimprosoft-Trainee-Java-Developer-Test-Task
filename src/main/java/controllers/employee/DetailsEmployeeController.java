package controllers.employee;

import models.Department;
import models.Employee;
import services.impl.DepartmentService;
import services.impl.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 7/04/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "EmployeeDepartment", urlPatterns = "/departments/employees/details")
public class DetailsEmployeeController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = departmentService.getById(departmentService.getDepId(req.getQueryString()));
        req.setAttribute("departmentId", department.getId());
        req.setAttribute("departmentTitle", department.getTitle());

        Employee employee = employeeService.getById(employeeService.getEmpId(req.getQueryString()));
        req.setAttribute("id", employee.getId());
        req.setAttribute("firstName", employee.getFirstName());
        req.setAttribute("lastName", employee.getLastName());
        req.setAttribute("email", employee.getEmail());
        req.setAttribute("salaryPerHour", employee.getSalaryPerHour());
        req.setAttribute("salaryPerYear", employee.getSalaryPerYear());
        req.setAttribute("dateOfBirth", employee.getDateOfBirth());
        req.setAttribute("age", employee.getAge());
        req.setAttribute("head", employee.getHead());
        req.getRequestDispatcher("/WEB-INF/views/employee/detailsEmployee.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}