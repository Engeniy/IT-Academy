package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;

public interface UserInfoConverter {

    User fromDTO(UserInfoDTO userInfoDTO);

    UserInfoDTO toDTO(User user);
}
