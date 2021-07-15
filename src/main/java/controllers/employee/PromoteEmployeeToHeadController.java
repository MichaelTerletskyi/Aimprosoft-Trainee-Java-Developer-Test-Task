package controllers.employee;

import enums.EmployeeFetchType;
import exceptions.EntityNotFoundException;
import exceptions.HeadOfDepartmentNotFoundException;
import models.Employee;
import services.impl.DepartmentService;
import services.impl.EmployeeService;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @Create 7/03/2021
 * @Extends of {@link HttpServlet} class.
 */

@WebServlet(name = "PromoteToHeadEmployee", urlPatterns = "/departments/employees/promote/to/head")
public class PromoteEmployeeToHeadController extends HttpServlet {
    private final DepartmentService departmentService = new DepartmentService();
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void init(ServletConfig config) {
        System.out.println(String.format("%s %s %s", "Servlet \"", config.getServletName(), "\" has been initialized"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long departmentId = departmentService.getDepId(req.getQueryString());

        try {
            Employee previousHead = departmentService.getById(departmentId, EmployeeFetchType.EAGER).getHead();
            if (!Objects.isNull(previousHead)) {
                previousHead.setHead(false);
                employeeService.update(previousHead);
            }
            Employee newHead = employeeService.getById(employeeService.getEmpId(req.getQueryString()));
            newHead.setHead(true);
            employeeService.update(newHead);
            resp.sendRedirect(req.getContextPath() + "/departments/employees?id=" + departmentId);
        } catch (HeadOfDepartmentNotFoundException | EntityNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        System.out.println(String.format("%s %s", "Servlet", "has been destroyed"));
    }
}