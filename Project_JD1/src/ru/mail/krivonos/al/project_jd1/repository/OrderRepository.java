package ru.mail.krivonos.al.project_jd1.repository;

import ru.mail.krivonos.al.project_jd1.repository.model.Order;

import java.sql.Connection;
import java.util.List;

public interface OrderRepository {

    void addOrder(Connection connection, Order order);

    void updateState(Connection connection, Order order);

    void delete(Connection connection, Order order);

    List<Order> findAllByUserID(Connection connection, Long id, Integer pageNumber);

    List<Order> findAll(Connection connection, Integer pageNumber);
}
