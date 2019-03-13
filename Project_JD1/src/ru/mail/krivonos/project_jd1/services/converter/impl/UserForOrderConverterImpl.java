package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.converter.UserForOrderConverter;
import ru.mail.krivonos.project_jd1.services.model.user.UserForOrderDTO;

public class UserForOrderConverterImpl implements UserForOrderConverter {

    private static UserForOrderConverter instance;

    private UserForOrderConverterImpl() {
    }

    public static UserForOrderConverter getInstance() {
        if (instance == null) {
            synchronized (UserForOrderConverterImpl.class) {
                if (instance == null) {
                    instance = new UserForOrderConverterImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User fromDTO(UserForOrderDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    @Override
    public UserForOrderDTO toDTO(User user) {
        UserForOrderDTO userDTO = new UserForOrderDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
