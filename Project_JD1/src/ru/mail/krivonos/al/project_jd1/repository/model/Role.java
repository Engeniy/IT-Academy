package ru.mail.krivonos.al.project_jd1.repository.model;

import java.util.List;

public class Role {

    private Long id;

    private List<Permissions> permissions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permissions> permissions) {
        this.permissions = permissions;
    }
}
