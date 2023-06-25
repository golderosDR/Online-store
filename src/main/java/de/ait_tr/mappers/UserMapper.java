package de.ait_tr.mappers;

import de.ait_tr.dtos.UserDTO;
import de.ait_tr.models.User;
import de.ait_tr.validators.UserValidator;

public class UserMapper {
    public static String ILLEGAL_FORMAT_OR_DAMAGED_FILE = "Файл содержит элементы неподдерживаемого формата или поврежден.";
    private static final String DELIMITER = ";";
    private static final int REQUIRED_SIZE = 5;

    private UserMapper() {
    }

    public static User toUser(String line) {
        try {
            String[] parsed = line.split(DELIMITER);
            User user;
            if (parsed.length == REQUIRED_SIZE) {
                String id = parsed[0];
                String name = parsed[1];
                String email = parsed[2];
                String password = parsed[3];
                boolean isAdmin = parsed[4].equals("1");
                user = new User(id, name, email, password, isAdmin);
            } else {
                throw new RuntimeException(ILLEGAL_FORMAT_OR_DAMAGED_FILE);
            }
            if (UserValidator.validate(user)) {
                return user;
            } else return null;

        } catch (RuntimeException e) {
            throw new RuntimeException(ILLEGAL_FORMAT_OR_DAMAGED_FILE + "  " + e.getMessage());
        }
    }
    public static String toLine(User user) {
        return user.getId() +
                ";" +
                user.getName() +
                ";" +
                user.getEmail() +
                ";" +
                user.getPassword() +
                ";" +
                (user.isAdmin()? "1": "0")
                ;

    }
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getId(), user.getName(), user.getEmail(), user.isAdmin());
    }
}
