package ru.mail.krivonos.project_jd1.services.converter.user;

import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.converter.RoleConverter;
import ru.mail.krivonos.project_jd1.services.converter.RoleConverterImpl;
import ru.mail.krivonos.project_jd1.services.model.user.AuthorizedUserDTO;

public class AuthorizedUserConverterImpl implements AuthorizedUserConverter {

    private static AuthorizedUserConverter instance;

    private RoleConverter roleConverter = RoleConverterImpl.getInstance();

    private AuthorizedUserConverterImpl() {
    }

    public static AuthorizedUserConverter getInstance() {
        if (instance == null) {
            instance = new AuthorizedUserConverterImpl();
        }
        return instance;
    }

    @Override

    public User fromDTO(AuthorizedUserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setRole(roleConverter.fromDTO(userDTO.getRole()));
        return user;
    }

    @Override
    public AuthorizedUserDTO toDTO(User user) {
        AuthorizedUserDTO userDTO = new AuthorizedUserDTO();
        userDTO.setId(user.getId());
        userDTO.setRole(roleConverter.toDTO(user.getRole()));
        return userDTO;
    }
}
