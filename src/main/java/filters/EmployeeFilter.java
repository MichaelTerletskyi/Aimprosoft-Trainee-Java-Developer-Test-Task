package filters;

import exceptions.ValidationException;
import services.impl.EmployeeService;
import validation.impl.EmployeeValidator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Create 7/16/2021
 * @Implements of {@link Filter} class.
 */

public class EmployeeFilter implements Filter {
    private final EmployeeValidator employeeValidator = new EmployeeValidator();
    private final EmployeeService employeeService = new EmployeeService();
    private final static String PATH_CREATE_JSP_URL = "/WEB-INF/views/employee/createEmployee.jsp";
    private final static String PATH_UPDATE_JSP_URL = "/WEB-INF/views/employee/updateEmployee.jsp";

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println(String.format("%s %s %s", "Filter \"", filterConfig.getFilterName(), "\" has been initialized"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        if (httpRequest.getMethod().equals("POST")) {
            try {
                employeeValidator.buildModel(httpRequest);
            } catch (ValidationException e) {
                e.printStackTrace();
                httpRequest.setAttribute("errors", e.getErrors());

                if (requestURI.equals("/departments/employees/add")) {
                    employeeService.attributesSets(httpRequest);
                    httpRequest.getRequestDispatcher(PATH_CREATE_JSP_URL).forward(request, response);
                }
                if (requestURI.equals("/departments/employees/edit")) {
                    employeeService.attributesSetsWithId(httpRequest);
                    httpRequest.getRequestDispatcher(PATH_UPDATE_JSP_URL).forward(request, response);
                }
            }
        }
        chain.doFilter(request, response);
    }
}