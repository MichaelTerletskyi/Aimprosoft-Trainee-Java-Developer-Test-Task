package controllers.employee;

import exceptions.ValidationException;
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
import java.util.Map;

/**
 * @Create 7/03/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "AddEmployee", urlPatterns = "/departments/employees/add")
public class CreateEmployeeController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();
    private final EmployeeService employeeService = new EmployeeService();
    private final EmployeeValidator employeeValidator = new EmployeeValidator();
    private final String pathUrl = "/WEB-INF/views/employee/createEmployee.jsp";
    private Boolean validationFailed = false;

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", departmentService.getById(EmployeesOfDepartmentController.DEPARTMENT_ID).getTitle());
        req.getRequestDispatcher(pathUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        try {
            Employee employee = employeeValidator.buildModel(req);
            employeeService.create(employee);
        } catch (ValidationException e) {
            Map<String, String> errors = e.getErrors();
            validationFailed = !errors.isEmpty();
            e.printStackTrace();
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(pathUrl).forward(req, resp);
        }

        if (!validationFailed) {
            departmentService.addEmployee(EmployeesOfDepartmentController.DEPARTMENT_ID, employeeService.getIdByEmail(req.getParameter("email")));
        }
        resp.sendRedirect(req.getContextPath() + "/departments/employees?id=" + EmployeesOfDepartmentController.DEPARTMENT_ID);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}