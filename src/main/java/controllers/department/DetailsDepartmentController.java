package controllers.department;

import enums.EmployeeFetchType;
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

@WebServlet(name = "DetailsDepartment", urlPatterns = "/departments/details")
public class DetailsDepartmentController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department department = departmentService.getById(Long.parseLong(req.getParameter("id")), EmployeeFetchType.EAGER);
        req.setAttribute("id", department.getId());
        req.setAttribute("title", department.getTitle());
        req.setAttribute("description", department.getDescription());
        req.setAttribute("amountOfEmployees", department.getEmployees().size());
        req.setAttribute("fullNameOfHead", department.getFullNameOfHead());
        req.setAttribute("averageSalaryPerHour", department.getAverageSalaryPerHour());
        req.setAttribute("totalSalary", department.getTotalSalary());
        req.setAttribute("averageAge", department.getAverageAge());
        req.getRequestDispatcher("/WEB-INF/views/department/detailsDepartment.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}