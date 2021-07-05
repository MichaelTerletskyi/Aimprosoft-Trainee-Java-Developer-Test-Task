package controllers.employee;

import models.Department;
import services.impl.DepartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 7/02/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "EmployeesOfDepartment", urlPatterns = "/departments/employees")
public class EmployeesOfDepartmentController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = departmentService.getById(Long.parseLong(req.getParameter("id")), true);
        req.setAttribute("departmentId", department.getId());
        req.setAttribute("departmentTitle", department.getTitle());
        req.setAttribute("employees", department.getEmployees());
        req.getRequestDispatcher("/WEB-INF/views/employee/employeesOfDepartment.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}