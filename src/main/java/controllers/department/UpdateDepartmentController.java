package controllers.department;

import models.Department;
import services.impl.DepartmentService;
import validation.impl.DepartmentValidator;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Create 7/01/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "UpdateDepartment", urlPatterns = "/departments/update")
public class UpdateDepartmentController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();
    private final DepartmentValidator departmentValidator = new DepartmentValidator();
    private final static String pathUrl = "/WEB-INF/views/department/updateDepartment.jsp";

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        departmentService.attributesSetsWithId(req);
        req.getRequestDispatcher(pathUrl).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Department department = departmentValidator.buildModel(req);
        departmentService.update(department);
        resp.sendRedirect(req.getContextPath() + "/departments");
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}