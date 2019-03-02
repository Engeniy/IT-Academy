package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

public interface UserService {

    void add(UserRegistrationDTO userRegistrationDTO);

    void updateInfo(UserInfoDTO userDTO);

    void updateEmail(Long userID, String email);

    void updatePassword(Long userID, String password);

    void deleteByID(Long id);

    AuthorizedUserDTO loginUser(UserLoginDTO userLoginDTO);

    UserInfoDTO getInfoByID(Long id);
}
