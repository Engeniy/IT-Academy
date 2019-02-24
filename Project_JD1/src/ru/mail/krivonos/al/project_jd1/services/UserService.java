package ru.mail.krivonos.al.project_jd1.services;

import ru.mail.krivonos.al.project_jd1.services.model.UserDTO;

public interface UserService {

    void add(UserDTO userDTO);

    void update(UserDTO userDTO);

    void deleteByID(Long id);

    UserDTO getUserByEmail(String email);
}
