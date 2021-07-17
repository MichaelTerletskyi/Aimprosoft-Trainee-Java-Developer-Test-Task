package filters;

import exceptions.ValidationException;
import services.impl.DepartmentService;
import validation.impl.DepartmentValidator;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Create 7/16/2021
 * @Implements of {@link Filter} class.
 */

public class DepartmentFilter implements Filter {
    private final DepartmentValidator departmentValidator = new DepartmentValidator();
    private final DepartmentService departmentService = new DepartmentService();
    private final static String PATH_CREATE_JSP_URL = "/WEB-INF/views/department/createDepartment.jsp";
    private final static String PATH_UPDATE_JSP_URL = "/WEB-INF/views/department/updateDepartment.jsp";

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
                departmentValidator.buildModel(httpRequest);
            } catch (ValidationException e) {
                e.printStackTrace();
                httpRequest.setAttribute("errors", e.getErrors());

                if (requestURI.equals("/departments/add")) {
                    departmentService.attributesSets(httpRequest);
                    httpRequest.getRequestDispatcher(PATH_CREATE_JSP_URL).forward(request, response);
                }
                if (requestURI.equals("/departments/update")) {
                    departmentService.attributesSetsWithId(httpRequest);
                    httpRequest.getRequestDispatcher(PATH_UPDATE_JSP_URL).forward(request, response);
                }
            }
        }
        chain.doFilter(request, response);
    }
}