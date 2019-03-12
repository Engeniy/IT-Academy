package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.converter.RoleConverter;
import ru.mail.krivonos.project_jd1.services.converter.UserRegistrationConverter;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

public class UserRegistrationConverterImpl implements UserRegistrationConverter {

    private static UserRegistrationConverter instance;

    private RoleConverter roleConverter = RoleConverterImpl.getInstance();

    private UserRegistrationConverterImpl() {
    }

    public static UserRegistrationConverter getInstance() {
        if (instance == null) {
            instance = new UserRegistrationConverterImpl();
        }
        return instance;
    }

    @Override
    public User fromDTO(UserRegistrationDTO userRegistrationDTO) {
        User user = new User();
        user.setSurname(userRegistrationDTO.getSurname());
        user.setName(userRegistrationDTO.getName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());
        return user;
    }

    @Override
    public UserRegistrationDTO toDTO(User user) {
        throw new UnsupportedOperationException();
    }
}
