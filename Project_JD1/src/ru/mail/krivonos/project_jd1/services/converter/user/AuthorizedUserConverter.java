package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;

public interface AuthorizedUserConverter {


    User fromDTO(AuthorizedUserDTO userDTO);

    AuthorizedUserDTO toDTO(User user);
}
