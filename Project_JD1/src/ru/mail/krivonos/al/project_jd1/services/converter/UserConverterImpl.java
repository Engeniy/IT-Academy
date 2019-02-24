package ru.mail.krivonos.al.project_jd1.services.converter;

import ru.mail.krivonos.al.project_jd1.repository.model.User;
import ru.mail.krivonos.al.project_jd1.services.model.UserDTO;

public class UserConverterImpl implements UserConverter {

    private static UserConverter instance;

    private UserConverterImpl() {
    }

    public static UserConverter getInstance() {
        if (instance == null) {
            instance = new UserConverterImpl();
        }
        return instance;
    }

    @Override
    public User fromDTO(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setSurname(userDTO.getSurname());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setRole(userDTO.getRole());
        return user;
    }

    @Override
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setSurname(user.getSurname());
        userDTO.setName(user.getName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
