package ru.mail.krivonos.project_jd1.services.model.user;

import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.services.model.RoleDTO;

public class AuthorizedUserDTO {

    private Long id;

    private PermissionsEnum permission;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PermissionsEnum getPermission() {
        return permission;
    }

    public void setPermission(PermissionsEnum permission) {
        this.permission = permission;
    }
}
