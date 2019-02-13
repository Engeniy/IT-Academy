package ru.mail.krivonos.al.additional.twentyone.testpractice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shipto")
public class ShipDestination {

    private String name;
    private String address;
    private String city;
    private String country;

    public String getName() {
        return name;
    }

    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    @XmlElement
    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    @XmlElement
    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    @XmlElement
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "ShipDestination{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
