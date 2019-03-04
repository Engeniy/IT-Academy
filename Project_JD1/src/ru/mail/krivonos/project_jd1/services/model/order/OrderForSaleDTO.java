package ru.mail.krivonos.project_jd1.services.model.order;

import ru.mail.krivonos.project_jd1.services.model.item.ItemForOrderDTO;
import ru.mail.krivonos.project_jd1.services.model.user.UserForOrderDTO;

import java.math.BigDecimal;
import java.util.Date;

public class OrderForSaleDTO {

    private Long id;

    private UserForOrderDTO user;

    private ItemForOrderDTO item;

    private Date dateOfCreation;

    private Integer quantity;

    private String state;

    private BigDecimal sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserForOrderDTO getUser() {
        return user;
    }

    public void setUser(UserForOrderDTO user) {
        this.user = user;
    }

    public ItemForOrderDTO getItem() {
        return item;
    }

    public void setItem(ItemForOrderDTO item) {
        this.item = item;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
