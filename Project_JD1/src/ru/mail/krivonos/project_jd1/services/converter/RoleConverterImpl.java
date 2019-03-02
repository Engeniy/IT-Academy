package ru.mail.krivonos.project_jd1.services.converter;

import ru.mail.krivonos.project_jd1.repository.model.Role;
import ru.mail.krivonos.project_jd1.services.model.RoleDTO;

public class RoleConverterImpl implements RoleConverter {

    private static RoleConverter instance;

    private RoleConverterImpl() {
    }

    public static RoleConverter getInstance() {
        if (instance == null) {
            instance = new RoleConverterImpl();
        }
        return instance;
    }

    @Override
    public Role fromDTO(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setPermissions(roleDTO.getPermissions());
        return role;
    }

    @Override
    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());
        roleDTO.setPermissions(role.getPermissions());
        return roleDTO;
    }
}
