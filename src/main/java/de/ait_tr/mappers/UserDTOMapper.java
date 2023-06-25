package de.ait_tr.mappers;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.dtos.UserDTO;
import de.ait_tr.models.User;

import java.util.UUID;

public class UserDTOMapper {
    private UserDTOMapper() {
    }

    public static String toLine(UserDTO userDTO) {
        return userDTO.id() + " " + userDTO.name() + " " + userDTO.email() + " " + (userDTO.isAdmin() ? "admin" : "");
    }

    public static User toUser(NewUserDTO newUserDTO) {
        return new User(
                UUID.randomUUID().toString(),
                newUserDTO.name(),
                newUserDTO.email(),
                newUserDTO.password(),
                false
        );
    }
    public static NewUserDTO toNewUserDTO(String newUserName, String newUserEmail, String newUserPassword) {
        return new NewUserDTO(newUserName, newUserEmail, newUserPassword);
    }
}
