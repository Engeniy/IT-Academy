package ru.mail.krivonos.project_jd1.services.model.order;

import ru.mail.krivonos.project_jd1.repository.model.OrderState;

import java.util.Date;

public class CreatedOrderDTO {

    private Long userID;

    private Long itemID;

    private Date dateOfCreation;

    private Integer quantity;

    private OrderState state;

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }

    public Long getItemID() {
        return itemID;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}
