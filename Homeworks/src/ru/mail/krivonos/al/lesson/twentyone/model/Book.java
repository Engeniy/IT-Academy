package ru.mail.krivonos.al.lesson.twentyone.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


@XmlRootElement(name = "book", namespace = "https://www.it-academy.by")
public class Book {

    private String id;
    private String author;
    private String title;
    private String genre;
    private double price;
    private Date publishDate;
    private String description;

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    @XmlElement(namespace = "https://www.it-academy.by")
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(namespace = "https://www.it-academy.by")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    @XmlElement(namespace = "https://www.it-academy.by")
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    @XmlElement(namespace = "https://www.it-academy.by")
    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    @XmlElement(namespace = "https://www.it-academy.by", name = "publish_date")
    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getDescription() {
        return description;
    }

    @XmlElement(namespace = "https://www.it-academy.by")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", price=" + price +
                ", publishDate=" + publishDate +
                ", description='" + description + '\'' +
                '}';
    }
}
