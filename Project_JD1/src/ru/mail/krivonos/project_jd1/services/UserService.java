package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.exceptions.PasswordChangeException;
import ru.mail.krivonos.project_jd1.services.exceptions.RegistrationException;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserLoginDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserRegistrationDTO;

public interface UserService {

    void add(UserRegistrationDTO userRegistrationDTO) throws RegistrationException;

    void updateInfo(UserInfoDTO userDTO);

    void updatePassword(String email, String oldPassword, String newPassword) throws PasswordChangeException;

    AuthorizedUserDTO loginUser(UserLoginDTO userLoginDTO);
}
