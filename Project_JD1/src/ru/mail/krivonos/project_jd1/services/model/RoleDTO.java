package ru.mail.krivonos.project_jd1.services.model;

import ru.mail.krivonos.project_jd1.repository.model.PermissionsEnum;
import ru.mail.krivonos.project_jd1.repository.model.RolesEnum;

import java.util.List;

public class RoleDTO {

    private Long id;

    private String name;

    private List<PermissionsEnum> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PermissionsEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionsEnum> permissions) {
        this.permissions = permissions;
    }
}
