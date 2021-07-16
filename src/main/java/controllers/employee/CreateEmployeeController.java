package controllers.employee;

import models.Department;
import models.Employee;
import services.impl.DepartmentService;
import services.impl.EmployeeService;
import validation.impl.EmployeeValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 7/03/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "AddEmployee", urlPatterns = "/departments/employees/add")
public class CreateEmployeeController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();
    private final EmployeeService employeeService = new EmployeeService();
    private final EmployeeValidator employeeValidator = new EmployeeValidator();
    private final static String pathUrl = "/WEB-INF/views/employee/createEmployee.jsp";

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = departmentService.getById(departmentService.extractId(req.getQueryString()));
        req.setAttribute("departmentId", department.getId());
        req.setAttribute("departmentTitle", department.getTitle());
        req.getRequestDispatcher(pathUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long departmentId = departmentService.extractId(req.getQueryString());
        Employee employee = employeeValidator.buildModel(req);
        employeeService.create(employee);
        departmentService.addEmployee(departmentId, employeeService.getIdByEmail(employee.getEmail()));
        resp.sendRedirect(req.getContextPath() + "/departments/employees?id=" + departmentId);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}