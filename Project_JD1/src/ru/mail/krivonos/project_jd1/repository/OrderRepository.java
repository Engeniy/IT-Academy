package ru.mail.krivonos.project_jd1.repository;

import ru.mail.krivonos.project_jd1.repository.exceptions.OrderRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;

import java.sql.Connection;
import java.util.List;

public interface OrderRepository {

    void add(Connection connection, Order order) throws OrderRepositoryException;

    Integer countPages(Connection connection) throws OrderRepositoryException;

    Integer countPagesForUser(Connection connection, Long id) throws OrderRepositoryException;

    Integer countPagesForState(Connection connection, OrderState state) throws OrderRepositoryException;

    void updateState(Connection connection, Long id, OrderState state) throws OrderRepositoryException;

    void delete(Connection connection, Order order) throws OrderRepositoryException;

    List<Order> findAllByUserID(Connection connection, Long id, Integer pageNumber) throws OrderRepositoryException;

    List<Order> findAll(Connection connection, Integer pageNumber) throws OrderRepositoryException;

    List<Order> findAllByOrderState(Connection connection, Integer pageNumber, OrderState orderState)
            throws OrderRepositoryException;
}
