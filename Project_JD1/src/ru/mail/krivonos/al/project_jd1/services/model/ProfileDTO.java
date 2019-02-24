package ru.mail.krivonos.al.project_jd1.services.model;

import ru.mail.krivonos.al.project_jd1.repository.model.User;

public class ProfileDTO {

    private User user;

    private String address;

    private String telephone;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
