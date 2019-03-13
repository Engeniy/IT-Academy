package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.converter.UserInfoConverter;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;

public class UserInfoConverterImpl implements UserInfoConverter {

    private static UserInfoConverter instance;

    private UserInfoConverterImpl() {
    }

    public static UserInfoConverter getInstance() {
        if (instance == null) {
            synchronized (UserInfoConverterImpl.class) {
                if (instance == null) {
                    instance = new UserInfoConverterImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public User fromDTO(UserInfoDTO userInfoDTO) {
        User user = new User();
        user.setSurname(userInfoDTO.getSurname());
        user.setName(userInfoDTO.getName());
        user.setEmail(userInfoDTO.getEmail());
        return user;
    }

    @Override
    public UserInfoDTO toDTO(User user) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setSurname(user.getSurname());
        userInfoDTO.setName(user.getName());
        userInfoDTO.setEmail(user.getEmail());
        return userInfoDTO;
    }
}
