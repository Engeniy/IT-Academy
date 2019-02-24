package ru.mail.krivonos.al.project_jd1.repository.impl;

import ru.mail.krivonos.al.project_jd1.repository.ItemRepository;
import ru.mail.krivonos.al.project_jd1.repository.model.Item;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    private static ItemRepository instance;

    private static final int LIMIT_VALUE = 10;

    private ItemRepositoryImpl() {
    }

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void addItem(Connection connection, Item item) {
        String sql = "INSERT INTO Item (name, description, unique_number, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getUniqueNumber());
            preparedStatement.setBigDecimal(4, item.getPrice());
            preparedStatement.executeUpdate();
            System.out.println("-------- Successfully Added Item With Name:" + item.getName() + "--------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItemsWithLessPrice(Connection connection, BigDecimal priceBorder) {
        String sql = "UPDATE Item SET deleted = TRUE WHERE price < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, priceBorder);
            int deleted = preparedStatement.executeUpdate();
            System.out.println("-------- " + deleted + " Items Was Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> findAll(Connection connection, Integer pageNumber) {
        String sql = "SELECT * FROM Item LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, LIMIT_VALUE);
            preparedStatement.setInt(2, LIMIT_VALUE * pageNumber);
            List<Item> items;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                items = getItems(resultSet);
            }
            System.out.println("-------- " + items.size() + " Items Was Found --------");
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> findItemsInPriceRange(Connection connection, BigDecimal rangeStart, BigDecimal rangeEnd,
                                            Integer pageNumber) {
        String sql = "SELECT * FROM Item WHERE price >= ? AND price <= ? AND deleted <> TRUE LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, rangeStart);
            preparedStatement.setBigDecimal(2, rangeEnd);
            preparedStatement.setInt(3, LIMIT_VALUE);
            preparedStatement.setInt(4, LIMIT_VALUE * pageNumber);
            List<Item> items;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                items = getItems(resultSet);
            }
            System.out.println("-------- " + items.size() + " Items Was Found --------");
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteByID(Connection connection, Long id) {
        String sql = "UPDATE Item SET deleted = TRUE WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("-------- Item Was Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updatePriceByID(Connection connection, Long id, BigDecimal price) {
        String sql = "UPDATE Item SET price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setLong(2, id);
            preparedStatement.executeUpdate();
            System.out.println("-------- Item Was Successfully Updated --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<Item> getItems(ResultSet resultSet) {
        List<Item> items = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Item item = new Item();
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String uniqueNumber = resultSet.getString("unique_number");
                BigDecimal price = resultSet.getBigDecimal("price");
                item.setId(id);
                item.setName(name);
                item.setDescription(description);
                item.setUniqueNumber(uniqueNumber);
                item.setPrice(price);
                items.add(item);
            }
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
