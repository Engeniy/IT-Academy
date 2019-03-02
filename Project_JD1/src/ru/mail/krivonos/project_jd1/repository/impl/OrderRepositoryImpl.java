package ru.mail.krivonos.project_jd1.repository.impl;

import ru.mail.krivonos.project_jd1.repository.OrderRepository;
import ru.mail.krivonos.project_jd1.repository.exceptions.OrderRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.repository.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {

    private static OrderRepository instance;

    private static final int LIMIT_VALUE = 10;

    private OrderRepositoryImpl() {
    }

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(Connection connection, Order order) throws OrderRepositoryException {
        String sql = "INSERT INTO `Order` (user_id, item_id, created, quantity, state) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getItem().getId());
            preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));
            preparedStatement.setInt(4, order.getQuantity());
            preparedStatement.setString(5, order.getState().name());
            int added = preparedStatement.executeUpdate();
            System.out.println("-------- " + added + " Order Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    @Override
    public void updateState(Connection connection, Order order) throws OrderRepositoryException {
        String sql = "UPDATE `Order` SET state = ? WHERE user_id = ? AND item_id = ? AND created = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, order.getState().name());
            preparedStatement.setLong(2, order.getUser().getId());
            preparedStatement.setLong(3, order.getItem().getId());
            preparedStatement.setTimestamp(4, new Timestamp(order.getDateOfCreation().getTime()));
            int updated = preparedStatement.executeUpdate();
            System.out.println("-------- " + updated + " Order State Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    @Override
    public void delete(Connection connection, Order order) throws OrderRepositoryException {
        String sql = "DELETE * FROM `Order` WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getId());
            int deleted = preparedStatement.executeUpdate();
            System.out.println("-------- " + deleted + " Order Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    @Override
    public List<Order> findAllByUserID(Connection connection, Long id, Integer pageNumber) throws OrderRepositoryException {
        String sql = "SELECT o.*, i.name, i.price, i.unique_number FROM `Order` o JOIN Item i ON o.item_id = i.id " +
                "WHERE o.user_id = ? LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, LIMIT_VALUE);
            preparedStatement.setInt(3, pageNumber * LIMIT_VALUE);
            List<Order> orders;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders = getOrdersForCustomer(resultSet);
            }
            System.out.println("-------- " + orders.size() + " Orders Was Found --------");
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    @Override
    public List<Order> findAll(Connection connection, Integer pageNumber) throws OrderRepositoryException {
        String sql = "SELECT o.*, i.name AS item_name, i.price, u.id AS user_id, u.name AS user_name, u.surname " +
                "FROM `Order` o JOIN Item i ON o.item_id = i.id " +
                "JOIN User u ON o.id = u.id LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, LIMIT_VALUE);
            preparedStatement.setInt(2, pageNumber * LIMIT_VALUE);
            List<Order> orders;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders = getOrdersForSale(resultSet);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    @Override
    public List<Order> findAllByOrderState(Connection connection, Integer pageNumber, OrderState orderState)
            throws OrderRepositoryException {
        String sql = "SELECT o.*, i.name AS item_name, i.price, u.id AS user_id, u.name AS user_name, u.surname " +
                "FROM `Order` o JOIN Item i ON o.item_id = i.id " +
                "JOIN User u ON o.id = u.id WHERE o.state = ? LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, LIMIT_VALUE);
            preparedStatement.setInt(2, pageNumber * LIMIT_VALUE);
            preparedStatement.setString(3, orderState.name());
            List<Order> orders;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders = getOrdersForSale(resultSet);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    private List<Order> getOrdersForSale(ResultSet resultSet) throws OrderRepositoryException {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDateOfCreation(new Date(resultSet.getTimestamp("created").getTime()));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setState(OrderState.valueOf(resultSet.getString("state")));
                User user = new User();
                user.setId(resultSet.getLong("user_id"));
                user.setSurname(resultSet.getString("surname"));
                user.setName(resultSet.getString("user_name"));
                order.setUser(user);
                Item item = new Item();
                item.setName(resultSet.getString("item_name"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setUniqueNumber(resultSet.getString("unique_number"));
                order.setItem(item);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }

    private List<Order> getOrdersForCustomer(ResultSet resultSet) throws OrderRepositoryException {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setDateOfCreation(new Date(resultSet.getTimestamp("created").getTime()));
                order.setQuantity(resultSet.getInt("quantity"));
                order.setState(OrderState.valueOf(resultSet.getString("state")));
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setUniqueNumber(resultSet.getString("unique_number"));
                order.setItem(item);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
    }
}
