package ru.mail.krivonos.al.additional.twentyone.testpractice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "item")
public class ShipItem {

    private String title;
    private String note;
    private int quantity;
    private BigDecimal price;

    public String getTitle() {
        return title;
    }

    @XmlElement
    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    @XmlElement
    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    @XmlElement
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @XmlElement
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ShipItem{" +
                "title='" + title + '\'' +
                ", note='" + note + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
