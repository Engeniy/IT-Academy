package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;

public interface UserLoginConverter {

    User fromDTO(UserLoginDTO userLoginDTO);

    UserLoginDTO toDTO(User user);
}
