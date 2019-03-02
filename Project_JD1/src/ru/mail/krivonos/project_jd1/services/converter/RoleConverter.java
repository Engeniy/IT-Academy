package ru.mail.krivonos.project_jd1.services.converter;

import ru.mail.krivonos.project_jd1.repository.model.Role;
import ru.mail.krivonos.project_jd1.services.model.RoleDTO;

public interface RoleConverter {

    Role fromDTO(RoleDTO roleDTO);

    RoleDTO toDTO(Role role);
}
