package ru.mail.krivonos.project_jd1.repository;

import ru.mail.krivonos.project_jd1.repository.exceptions.ItemRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemRepository {

    void add(Connection connection, Item item) throws ItemRepositoryException;

    void addItems(Connection connection, List<Item> items) throws ItemRepositoryException;

    Item findByUniqueNumber(Connection connection, String uniqueNumber) throws ItemRepositoryException;

    List<Item> findAll(Connection connection, Integer pageNumber) throws ItemRepositoryException;

    Integer countPages(Connection connection) throws ItemRepositoryException;

    void deleteByID(Connection connection, Long id) throws ItemRepositoryException;
}
