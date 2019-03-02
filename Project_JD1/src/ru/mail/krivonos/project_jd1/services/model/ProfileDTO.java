package ru.mail.krivonos.project_jd1.services.model;

import ru.mail.krivonos.project_jd1.services.model.user.UserInfoDTO;

public class ProfileDTO {

    private UserInfoDTO user;

    private String address;

    private String telephone;

    public UserInfoDTO getUser() {
        return user;
    }

    public void setUser(UserInfoDTO user) {
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
