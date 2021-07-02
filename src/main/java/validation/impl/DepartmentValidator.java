package validation.impl;

import exceptions.EntityNotFoundException;
import models.Department;
import services.impl.DepartmentService;
import validation.Validator;

import javax.servlet.http.HttpServletRequest;

import java.util.Objects;

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
        boolean existByTitle = service().existByTitle(department.getTitle());
        Long idByTitle = null;
        try {
            idByTitle = service().getIdByTitle(department.getTitle());
        } catch (EntityNotFoundException e) {
            e.printStackTrace();
        }
        if (Objects.isNull(department.getId())) return existByTitle;
        if (Objects.isNull(idByTitle)) return existByTitle;
        return !idByTitle.equals(department.getId()) && existByTitle;
    }

    @Override
    protected String validationFailedMessage() {
        return "Department validation has been failed";
    }

    @Override
    protected Department getPrimalModel(HttpServletRequest req) {
        return Objects.isNull(req.getParameter("id"))
                ? new Department(req.getParameter("title"), req.getParameter("description"))
                : new Department(Long.parseLong(req.getParameter("id")), req.getParameter("title"), req.getParameter("description"));
    }
}