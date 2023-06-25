package de.ait_tr.validators;

import de.ait_tr.exceptions.IllegalUserArgumentException;
import de.ait_tr.models.User;

import static de.ait_tr.exceptions.IllegalUserArgumentException.*;

public class UserValidator {
    private UserValidator() {}
    public static boolean validate(User user) {
        StringBuilder errors = new StringBuilder();
        if (!user.getId().matches("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")) {
            errors.append(WRONG_USER_ID_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (user.getName().matches("\\W+") || user.getName().length() < 4) {
            errors.append(WRONG_USER_NAME_PARAMETERS_MESSAGE)
                    .append(" ");
        }
        if (!user.getEmail().matches("\\w+@\\w+.\\w+")) {
            errors.append(WRONG_USER_EMAIL_FORMAT_MESSAGE)
                    .append(" ");
        }
        if (!errors.isEmpty()) {
            throw new IllegalUserArgumentException(errors.toString());
        }
        return true;
    }
}
