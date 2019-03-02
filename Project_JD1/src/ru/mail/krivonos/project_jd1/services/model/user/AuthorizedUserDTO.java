package ru.mail.krivonos.project_jd1.services.model.user;

import ru.mail.krivonos.project_jd1.services.model.RoleDTO;

public class AuthorizedUserDTO {

    private Long id;

    private RoleDTO role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
}
