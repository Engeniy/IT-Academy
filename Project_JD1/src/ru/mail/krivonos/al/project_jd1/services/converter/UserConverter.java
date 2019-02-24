package ru.mail.krivonos.al.project_jd1.services.converter;

import ru.mail.krivonos.al.project_jd1.repository.model.User;
import ru.mail.krivonos.al.project_jd1.services.model.UserDTO;

public interface UserConverter {

    User fromDTO(UserDTO userDTO);

    UserDTO toDTO(User user);
}
