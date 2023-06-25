package de.ait_tr.repositories;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.dtos.UserDTO;

import java.util.List;

public interface UserRepository {
    List<UserDTO> findAll();
    UserDTO findById(String id);
    UserDTO findByName(String name);
    UserDTO findByEmail(String email);
    List<UserDTO> find(String searchInfo);
    void add(NewUserDTO newUserDTO);
    void update(UserDTO userDTO);
    void remove(UserDTO userDTO);
    boolean containsById(String id);
    boolean containsByName(String name);
    boolean containsByEmail(String email);
    boolean isPasswordCorrect(String userId, String password);
    boolean checkNewPassword(String userId, String newPassword);
    void changePassword(String userId, String newPassword);
}
