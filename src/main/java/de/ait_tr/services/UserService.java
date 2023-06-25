package de.ait_tr.services;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.dtos.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    UserDTO findById(String id);
    UserDTO findByName(String name);
    UserDTO findByEmail(String email);
    List<UserDTO> find(String searchInfo);
    boolean add(NewUserDTO newUserDTO);
    boolean update(UserDTO userDTO);
    boolean remove(UserDTO userDTO);
    boolean changePassword(String userId, String password);
    boolean isPasswordCorrect(String userId, String password);
}
