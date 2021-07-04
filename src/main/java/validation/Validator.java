package validation;

import exceptions.ValidationException;
import services.AbstractService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Create 7/01/2021
 */

public abstract class Validator<T> {
    private ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    private javax.validation.Validator validator = validatorFactory.getValidator();
    private Map<String, String> errorsMap = new HashMap<>();

    protected abstract AbstractService<T> service();
    protected abstract String uniqueFieldError();
    protected abstract boolean existByUniqueField(T t);
    protected abstract String validationFailedMessage();
    protected abstract T getPrimalModel(HttpServletRequest req, Map<String, String> errorsMap);
    protected abstract void additionalLocalCheck(HttpServletRequest req, Map<String, String> errorsMap);

    public T buildModel(HttpServletRequest req) {
        additionalLocalCheck(req, errorsMap);
        T model = getPrimalModel(req, errorsMap);
        return validate(model);
    }

    private T validate(T model) {
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        violations.forEach(v -> errorsMap.put(getCamelCaseErrorTitle(v.getMessage()), v.getMessage()));
        if (existByUniqueField(model)) {
            errorsMap.put(getCamelCaseErrorTitle(uniqueFieldError()), uniqueFieldError());
        }
        if (errorsMap.size() > 0)
            throw new ValidationException(validationFailedMessage(), errorsMap);
        return model;
    }

    protected String getCamelCaseErrorTitle(String s) {
        String[] strings = s.strip().split(" ");

        StringBuilder sb = new StringBuilder();
        for (String currentString : strings) {
            sb.append(String.valueOf(currentString.charAt(0)).toUpperCase());
            sb.append(currentString.substring(1));
        }
        return sb.toString();
    }
}