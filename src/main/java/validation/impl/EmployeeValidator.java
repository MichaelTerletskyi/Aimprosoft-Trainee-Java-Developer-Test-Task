package validation.impl;

import exceptions.EntityNotFoundException;
import models.Employee;
import services.impl.EmployeeService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static validation.IEmployeeValidationMessages.EMPLOYEE_EMAIL_UNIQUE_ERROR_MESSAGE;

public class EmployeeValidator extends Validator<Employee> {

    @Override
    protected EmployeeService service() {
        return new EmployeeService();
    }

    @Override
    protected String uniqueFieldError() {
        return EMPLOYEE_EMAIL_UNIQUE_ERROR_MESSAGE;
    }

    @Override
    protected boolean existByUniqueField(Employee employee) {
        boolean existByTitle = service().existByEmail(employee.getEmail());
        Long idByEmail = null;
        try {
            idByEmail = service().getIdByEmail(employee.getEmail());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(employee.getId())) return existByTitle;
        if (Objects.isNull(idByEmail)) return existByTitle;
        return !idByEmail.equals(employee.getId()) && existByTitle;
    }

    @Override
    protected String validationFailedMessage() {
        return "Employee validation has been failed";
    }

    @Override
    protected Employee getPrimalModel(HttpServletRequest req) {
        return Objects.isNull(req.getParameter("id"))
                ? new Employee(req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("email"), BigDecimal.valueOf(Long.parseLong(req.getParameter("salaryPerHour"))),
                LocalDate.parse(req.getParameter("dateOfBirth")), false)
                : new Employee(Long.parseLong(req.getParameter("id")), req.getParameter("firstName"), req.getParameter("lastName"),
                req.getParameter("email"), BigDecimal.valueOf(Long.parseLong(req.getParameter("salaryPerHour"))),
                LocalDate.parse(req.getParameter("dateOfBirth")), false);
    }
}