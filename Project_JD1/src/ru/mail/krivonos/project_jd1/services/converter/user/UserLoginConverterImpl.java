package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;

public class UserLoginConverterImpl implements UserLoginConverter {

    @Override
    public User fromDTO(UserLoginDTO userLoginDTO) {
        User user = new User();
        user.setEmail(userLoginDTO.getEmail());
        user.setPassword(userLoginDTO.getPassword());
        return user;
    }

    @Override
    public UserLoginDTO toDTO(User user) {
        throw new UnsupportedOperationException();
    }
}
