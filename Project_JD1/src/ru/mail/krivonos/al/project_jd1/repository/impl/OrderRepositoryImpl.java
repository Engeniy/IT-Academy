package ru.mail.krivonos.al.project_jd1.repository.impl;

import ru.mail.krivonos.al.project_jd1.repository.OrderRepository;
import ru.mail.krivonos.al.project_jd1.repository.model.Item;
import ru.mail.krivonos.al.project_jd1.repository.model.Order;
import ru.mail.krivonos.al.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.al.project_jd1.repository.model.User;

import java.math.BigDecimal;
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
    public void addOrder(Connection connection, Order order) {
        String sql = "INSERT INTO `Order` (user_id, item_id, created, quantity, state) VALUES (?, ?, NOW(), ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getItem().getId());
            preparedStatement.setInt(3, order.getQuantity());
            preparedStatement.setString(4, order.getState().name());
            preparedStatement.executeUpdate();
            System.out.println("-------- Order Successfully Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateState(Connection connection, Order order) {
        String sql = "UPDATE `Order` SET state = ? WHERE user_id = ? AND item_id = ? AND created = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, order.getState().name());
            preparedStatement.setLong(2, order.getUser().getId());
            preparedStatement.setLong(3, order.getItem().getId());
            preparedStatement.setTimestamp(4, new Timestamp(order.getCreationDate().getTime()));
            System.out.println("-------- Order State Successfully Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Connection connection, Order order) {
        String sql = "DELETE * FROM `Order` WHERE user_id = ? AND item_id = ? AND created = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, order.getUser().getId());
            preparedStatement.setLong(2, order.getItem().getId());
            preparedStatement.setTimestamp(3, new Timestamp(order.getCreationDate().getTime()));
            System.out.println("-------- Order Successfully Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAllByUserID(Connection connection, Long id, Integer pageNumber) {
        String sql = "SELECT o.*, i.name, i.price FROM `Order` o JOIN Item i ON o.item_id = i.id " +
                "WHERE o.user_id = ? LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.setInt(2, LIMIT_VALUE);
            preparedStatement.setInt(3, pageNumber * LIMIT_VALUE);
            List<Order> orders;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders = getOrders(resultSet);
            }
            System.out.println("-------- " + orders.size() + " Orders Was Found --------");
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Order> findAll(Connection connection, Integer pageNumber) {
        String sql = "SELECT o.*, i.name, i.price FROM `Order` o JOIN Item i ON o.item_id = i.id LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, LIMIT_VALUE);
            preparedStatement.setInt(2, pageNumber * LIMIT_VALUE);
            List<Order> orders;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                orders = getOrders(resultSet);
            }
            System.out.println("-------- " + orders.size() + " Orders Was Found --------");
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Order> getOrders(ResultSet resultSet) {
        List<Order> orders = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Order order = new Order();
                Date creationDate = new Date(resultSet.getTimestamp("created").getTime());
                order.setCreationDate(creationDate);
                Integer quantity = resultSet.getInt("quantity");
                order.setQuantity(quantity);
                OrderState orderState = OrderState.valueOf(resultSet.getString("state"));
                order.setState(orderState);
                Long userID = resultSet.getLong("user_id");
                User user = new User();
                user.setId(userID);
                order.setUser(user);
                Long itemID = resultSet.getLong("item_id");
                String itemName = resultSet.getString("name");
                BigDecimal price = resultSet.getBigDecimal("price");
                Item item = new Item();
                item.setId(itemID);
                item.setName(itemName);
                item.setPrice(price);
                order.setItem(item);
                orders.add(order);
            }
            return orders;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
