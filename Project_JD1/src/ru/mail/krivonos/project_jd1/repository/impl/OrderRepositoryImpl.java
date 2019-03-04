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

    private static final int LIMIT_VALUE = 9;

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
    public Integer countPages(Connection connection) throws OrderRepositoryException {
        String sql = "SELECT COUNT(*) AS lines_number FROM `Order`";
        int pagesNumber = 0;
        try (Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(sql)) {
                if (resultSet.next()) {
                    int lines_number = resultSet.getInt("lines_number");
                    pagesNumber = lines_number / LIMIT_VALUE;
                    if (lines_number % LIMIT_VALUE > 0) {
                        pagesNumber += 1;
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
        return pagesNumber;
    }

    @Override
    public Integer countPagesForUser(Connection connection, Long id) throws OrderRepositoryException {
        String sql = "SELECT COUNT(*) AS lines_number FROM `Order` WHERE user_id = ?";
        int pagesNumber = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int lines_number = resultSet.getInt("lines_number");
                    pagesNumber = lines_number / LIMIT_VALUE;
                    if (lines_number % LIMIT_VALUE > 0) {
                        pagesNumber += 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
        return pagesNumber;
    }

    @Override
    public Integer countPagesForState(Connection connection, OrderState state) throws OrderRepositoryException {
        String sql = "SELECT COUNT(*) AS lines_number FROM `Order` WHERE state = ?";
        int pagesNumber = 0;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, state.name());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int lines_number = resultSet.getInt("lines_number");
                    pagesNumber = lines_number / LIMIT_VALUE;
                    if (lines_number % LIMIT_VALUE > 0) {
                        pagesNumber += 1;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new OrderRepositoryException(e);
        }
        return pagesNumber;
    }

    @Override
    public void updateState(Connection connection, Long id, OrderState state) throws OrderRepositoryException {
        String sql = "UPDATE `Order` SET state = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, state.name());
            preparedStatement.setLong(2, id);
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
            preparedStatement.setInt(3, (pageNumber - 1) * LIMIT_VALUE);
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
        String sql = "SELECT o.*, i.name AS item_name, i.price, i.unique_number, u.id AS user_id, " +
                "u.email AS user_email, u.name AS user_name, u.surname " +
                "FROM `Order` o LEFT JOIN Item i ON o.item_id = i.id " +
                "LEFT JOIN User u ON o.user_id = u.id LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, LIMIT_VALUE);
            preparedStatement.setInt(2, (pageNumber - 1) * LIMIT_VALUE);
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
        String sql = "SELECT o.*, i.name AS item_name, i.price, i.unique_number, u.id AS user_id, " +
                "u.email AS user_email, u.name AS user_name, u.surname " +
                "FROM `Order` o JOIN Item i ON o.item_id = i.id " +
                "JOIN User u ON o.user_id = u.id WHERE o.state = ? LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, orderState.name());
            preparedStatement.setInt(2, LIMIT_VALUE);
            preparedStatement.setInt(3, (pageNumber - 1) * LIMIT_VALUE);
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
                user.setEmail(resultSet.getString("user_email"));
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
