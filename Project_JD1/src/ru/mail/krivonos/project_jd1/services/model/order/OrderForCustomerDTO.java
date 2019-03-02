package ru.mail.krivonos.project_jd1.services.model.order;

import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.services.model.item.ItemForOrderDTO;

import java.math.BigDecimal;
import java.util.Date;

public class OrderForCustomerDTO {

    private ItemForOrderDTO item;

    private Date dateOfCreation;

    private Integer quantity;

    private OrderState state;

    private BigDecimal sum;

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

    public OrderState getState() {
        return state;
    }

    public void setState(OrderState state) {
        this.state = state;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
