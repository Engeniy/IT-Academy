package ru.mail.krivonos.al.test.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.List;

@XmlRootElement(name = "PurchaseOrder")
public class PurchaseOrder {

    private Date orderDate;
    private List<Item> items;

    public Date getOrderDate() {
        return orderDate;
    }

    @XmlAttribute(name = "OrderDate")
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public List<Item> getItems() {
        return items;
    }

    @XmlElement(name = "Item")
    public void setItems(List<Item> items) {
        this.items = items;
    }
}
