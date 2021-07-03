package controllers.employee;

import services.impl.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 7/03/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "DeleteEmployee", urlPatterns = "/departments/employees/delete")
public class DeleteEmployeeController extends HttpServlet {
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        employeeService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/departments/employees?id=" + EmployeesOfDepartmentController.DEPARTMENT_ID);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}