package kr.co.ppm.system.admin;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Admin.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Admin admin = (Admin) target;
        if (admin.getId() == ""
                || admin.getPassword() == "") {
            errors.rejectValue("id", "required");
            errors.rejectValue("password", "required");
        } else if (whitespaceCheck(admin.getId())
                || whitespaceCheck(admin.getPassword())) {
            errors.rejectValue("id", "whitespace");
            errors.rejectValue("password", "whitespace");
        }
    }

    private boolean whitespaceCheck(String checkString) {
        for (int i = 0; i < checkString.length(); i++) {
            if (checkString.charAt(i) == ' ') {
                return true;
            }
        }

        return false;
    }
}
