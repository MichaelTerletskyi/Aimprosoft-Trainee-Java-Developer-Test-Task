package validation.impl;

import models.Department;
import services.impl.DepartmentService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;

import static validation.IDepartmentValidationErrorsMessages.DEPARTMENT_TITLE_UNIQUE_ERROR_MESSAGE;

/**
 * @Create 7/01/2021
 * @Extends of {@link Validator} class.
 */

public class DepartmentValidator extends Validator<Department> {

    @Override
    protected DepartmentService service() {
        return new DepartmentService();
    }

    @Override
    protected String uniqueFieldError() {
        return DEPARTMENT_TITLE_UNIQUE_ERROR_MESSAGE;
    }

    @Override
    protected boolean existByUniqueField(Department department) {
        return service().existByTitle(department.getTitle());
    }

    @Override
    protected String validationFailedMessage() {
        return "Department validation has been failed";
    }

    @Override
    protected Department getPrimalModel(HttpServletRequest req) {
        return new Department(req.getParameter("title"), req.getParameter("description"));
    }
}