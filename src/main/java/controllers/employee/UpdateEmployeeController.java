package controllers.employee;

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


@WebServlet(name = "EditEmployee", urlPatterns = "/departments/employees/edit")
public class UpdateEmployeeController extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService();
    private final EmployeeValidator employeeValidator = new EmployeeValidator();
    private final static String pathUrl = "/WEB-INF/views/employee/updateEmployee.jsp";

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        employeeService.attributesSetsWithId(req);
        req.getRequestDispatcher(pathUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Employee employee = employeeValidator.buildModel(req);
        employeeService.update(employee);
        resp.sendRedirect(req.getContextPath() + "/departments/employees?id=" +
                departmentService.getDepId(req.getQueryString()));
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}