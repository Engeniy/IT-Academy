package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

public interface UserRegistrationConverter {

    User fromDTO(UserRegistrationDTO userRegistrationDTO);

    UserRegistrationDTO toDTO(User user);
}
