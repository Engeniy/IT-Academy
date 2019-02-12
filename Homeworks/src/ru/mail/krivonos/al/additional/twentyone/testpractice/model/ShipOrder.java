package ru.mail.krivonos.al.additional.twentyone.testpractice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "shiporder")
public class ShipOrder {

//    @XmlElement(name = "orderperson")
    private String orderPerson;
//    @XmlElement(name = "shipto")
    private ShipDestination destination;
//    @XmlElement(name = "item")
    private List<ShipItem> items;

    public String getOrderPerson() {
        return orderPerson;
    }

    @XmlElement(name = "orderperson")
    public void setOrderPerson(String orderPerson) {
        this.orderPerson = orderPerson;
    }

    public ShipDestination getDestination() {
        return destination;
    }

    @XmlElement(name = "shipto")
    public void setDestination(ShipDestination destination) {
        this.destination = destination;
    }

    public List<ShipItem> getItems() {
        return items;
    }

    @XmlElement(name = "item")
    public void setItems(List<ShipItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "ShipOrder{" +
                "orderPerson='" + orderPerson + '\'' +
                ", destination=" + destination +
                ", items=" + items +
                '}';
    }
}
