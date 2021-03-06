package controllers.department;

import enums.EmployeeFetchType;
import services.impl.DepartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 6/29/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "AllDepartments", urlPatterns = "/departments")
public class AllDepartmentsController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("departments", departmentService.getAll(EmployeeFetchType.EAGER));
        req.getRequestDispatcher("/WEB-INF/views/department/allDepartments.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}