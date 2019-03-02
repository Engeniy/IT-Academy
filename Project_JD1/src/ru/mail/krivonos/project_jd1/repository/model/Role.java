package ru.mail.krivonos.project_jd1.repository.model;

import java.util.List;

public class Role {

    private Long id;

    private RolesEnum name;

    private List<PermissionsEnum> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolesEnum getName() {
        return name;
    }

    public void setName(RolesEnum name) {
        this.name = name;
    }

    public List<PermissionsEnum> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<PermissionsEnum> permissions) {
        this.permissions = permissions;
    }
}
