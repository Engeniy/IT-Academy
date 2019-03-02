package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.model.user.UserForOrderDTO;

public interface UserForOrderConverter {

    User fromDTO(UserForOrderDTO userDTO);

    UserForOrderDTO toDTO(User user);
}
