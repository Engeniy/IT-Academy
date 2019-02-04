package ru.mail.krivonos.al.lesson.twentyone.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(namespace = "https://www.it-academy.by", name = "catalog")
public class Catalog {

    private List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    @XmlElement(name = "book", namespace = "https://www.it-academy.by")
    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Catalog:");
        stringBuilder.append(System.lineSeparator());
        for (Book book : books) {
            stringBuilder.append(book);
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}
