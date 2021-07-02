package controllers.department;

import services.impl.DepartmentService;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 7/01/2021
 * @Extends of {@link HttpServlet} class.
 */

public class DeleteDepartmentController extends HttpServlet {
    private DepartmentService departmentService = new DepartmentService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        departmentService.delete(Long.parseLong(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/departments");
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}