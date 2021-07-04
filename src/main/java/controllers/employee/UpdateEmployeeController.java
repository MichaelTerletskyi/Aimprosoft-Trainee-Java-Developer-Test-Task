package controllers.employee;

import exceptions.ValidationException;
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
import java.util.Map;

@WebServlet(name = "EditEmployee", urlPatterns = "/departments/employees/edit")
public class UpdateEmployeeController extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService();
    private final EmployeeValidator employeeValidator = new EmployeeValidator();
    private final String pathUrl = "/WEB-INF/views/employee/updateEmployee.jsp";

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = employeeService.getById(Long.parseLong(req.getParameter("id")));
        Department department = departmentService.getById(EmployeesOfDepartmentController.DEPARTMENT_ID);
        req.setAttribute("departmentId", department.getId());
        req.setAttribute("departmentTitle", department.getTitle());
        req.setAttribute("firstName", employee.getFirstName());
        req.setAttribute("lastName", employee.getLastName());
        req.setAttribute("email", employee.getEmail());
        req.setAttribute("salaryPerHour", employee.getSalaryPerHour());
        req.setAttribute("dateOfBirth", employee.getDateOfBirth());
        req.getRequestDispatcher(pathUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Employee employee = employeeValidator.buildModel(req);
            employeeService.update(employee);
        } catch (ValidationException e) {
            Map<String, String> errors = e.getErrors();
            e.printStackTrace();
            req.setAttribute("errors", errors);
            req.getRequestDispatcher(pathUrl).forward(req, resp);
        }
        resp.sendRedirect(req.getContextPath() + "/departments/employees?id=" + EmployeesOfDepartmentController.DEPARTMENT_ID);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}