package de.ait_tr.services;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.dtos.UserDTO;
import de.ait_tr.repositories.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;

    public UserServiceImpl(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDTO findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public UserDTO findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public UserDTO findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDTO> find(String searchInfo) {
        return userRepository.find(searchInfo);
    }

    @Override
    public boolean add(NewUserDTO newUserDTO) {
        if (userRepository.containsByName(newUserDTO.name()) ||
                userRepository.containsByEmail(newUserDTO.email())) {
            return false;
        } else {
            userRepository.add(newUserDTO);
            return true;
        }
    }

    @Override
    public boolean update(UserDTO userDTO) {
        if (userRepository.containsById(userDTO.id())) {
            userRepository.update(userDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(UserDTO userDTO) {
        if (userRepository.containsById(userDTO.id())) {
            userRepository.remove(userDTO);
            return true;
        }
        return false;
    }

    @Override
    public boolean changePassword(String userId, String newPassword) {
        if (!userRepository.checkNewPassword(userId, newPassword)) {
            userRepository.changePassword(userId, newPassword);
            return true;
        }
        return false;
    }

    @Override
    public boolean isPasswordCorrect(String userId, String password) {
        return userRepository.isPasswordCorrect(userId, password);
    }


}
