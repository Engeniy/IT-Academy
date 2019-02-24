package ru.mail.krivonos.al.project_jd1.repository;

import ru.mail.krivonos.al.project_jd1.repository.model.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.List;

public interface ItemRepository {

    void addItem(Connection connection, Item item);

    void deleteItemsWithLessPrice(Connection connection, BigDecimal priceBorder);

    List<Item> findAll(Connection connection, Integer pageNumber);

    List<Item> findItemsInPriceRange(Connection connection, BigDecimal rangeStart, BigDecimal rangeEnd,
                                     Integer pageNumber);

    void deleteByID(Connection connection, Long id);

    void updatePriceByID(Connection connection, Long id, BigDecimal price);
}
