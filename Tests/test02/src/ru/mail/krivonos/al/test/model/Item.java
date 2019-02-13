package ru.mail.krivonos.al.test.model;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

@XmlRootElement(name = "Item")
public class Item {

    private String partNumber;
    private String productName;
    private int quantity;
    private BigDecimal usPrice;
    private String comment;
    private Date shipDate;

    public String getPartNumber() {
        return partNumber;
    }

    @XmlAttribute(name = "PartNumber")
    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getProductName() {
        return productName;
    }

    @XmlElement(name = "ProductName")
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    @XmlElement(name = "Quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUsPrice() {
        return usPrice;
    }

    @XmlElement(name = "USPrice")
    public void setUsPrice(BigDecimal usPrice) {
        this.usPrice = usPrice;
    }

    public String getComment() {
        return comment;
    }

    @XmlElement(name = "Comment")
    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getShipDate() {
        return shipDate;
    }

    @XmlElement(name = "ShipDate")
    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }
}
