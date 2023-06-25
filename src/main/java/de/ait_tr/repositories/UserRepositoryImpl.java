package de.ait_tr.repositories;

import de.ait_tr.dtos.NewUserDTO;
import de.ait_tr.dtos.UserDTO;
import de.ait_tr.mappers.UserDTOMapper;
import de.ait_tr.mappers.UserMapper;
import de.ait_tr.models.User;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {
    private final String filePath;
    private final static String FILE_NOT_FOUND_ERROR_MSG = "Файл не найден или поврежден!";

    public UserRepositoryImpl(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<UserDTO> findAll() {
        return getAll().stream()
                .map(UserMapper::toUserDTO)
                .toList();
    }

    private List<User> getAll() {
        try {
            return Files.readAllLines(new File(filePath).toPath())
                    .stream()
                    .map(UserMapper::toUser)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }

    @Override
    public UserDTO findById(String id) {
        User user = findByUserId(id);
        if (user != null) {
            return UserMapper.toUserDTO(user);
        }
        return null;
    }

    private User findByUserId(String userId) {
        return getAll().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserDTO findByName(String name) {
        User user = findByUserName(name);
        if (user != null) {
            return UserMapper.toUserDTO(user);
        }
        return null;
    }

    private User findByUserName(String userName) {
        return getAll().stream()
                .filter(user -> user.getName().equals(userName))
                .findFirst()
                .orElse(null);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = findByUserEmail(email);
        if (user != null) {
            return UserMapper.toUserDTO(user);
        }
        return null;
    }

    private User findByUserEmail(String userEmail) {
        return getAll().stream()
                .filter(user -> user.getEmail().equals(userEmail))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<UserDTO> find(String searchInfo) {
        return getAll().stream()
                .filter(
                        user -> (user.getName().toLowerCase().contains(searchInfo.toLowerCase())) ||
                                user.getEmail().toLowerCase().contains(searchInfo.toLowerCase())
                )
                .map(UserMapper::toUserDTO)
                .toList();
    }

    @Override
    public void add(NewUserDTO newUserDTO) {
        User newUser = UserDTOMapper.toUser(newUserDTO);
        String line = UserMapper.toLine(newUser);

        try (FileWriter writer = new FileWriter(filePath, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(System.lineSeparator() + line);

        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        List<User> newUserList = getAll();
        for (User user : newUserList) {
            if (user.getId().equals(userDTO.id())) {
                user.setName(user.getName());
                user.setEmail(userDTO.email());
            }
        }
        save(newUserList);
    }

    @Override
    public void remove(UserDTO userDTO) {
        List<User> newUserList = getAll();
        User userToRemove = findByUserId(userDTO.id());

        newUserList.remove(userToRemove);
        save(newUserList);

    }

    @Override
    public boolean containsById(String id) {
        return (findByUserId(id) != null);
    }

    @Override
    public boolean containsByName(String name) {
        return (findByUserName(name) != null);
    }

    @Override
    public boolean containsByEmail(String email) {
        return (findByUserEmail(email) != null);
    }

    private void save(List<User> userList) {

        String lines = userList.stream()
                .map(UserMapper::toLine)
                .collect(Collectors.joining(System.lineSeparator()));

        try (FileWriter writer = new FileWriter(filePath);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            bufferedWriter.write(lines);

        } catch (IOException e) {
            throw new RuntimeException(FILE_NOT_FOUND_ERROR_MSG);
        }
    }

    @Override
    public boolean isPasswordCorrect(String userId, String password) {
        User userToCheck = findByUserId(userId);
        if (userToCheck!=null) {
            return userToCheck.getPassword().equals(password);
        }
        return false;


    }

    @Override
    public boolean checkNewPassword(String userId, String newPassword) {
        User userToCheck = findByUserId(userId);
        return userToCheck.getPassword().equals(newPassword);
    }

    @Override
    public void changePassword(String userId, String password) {
        List<User> newUserList = getAll();
        for (User user : newUserList) {
            if (user.getId().equals(userId)) {
                user.setPassword(password);
            }
        }
        save(newUserList);
    }
}
