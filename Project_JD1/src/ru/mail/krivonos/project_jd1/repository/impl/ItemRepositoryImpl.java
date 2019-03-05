package ru.mail.krivonos.project_jd1.repository.impl;

import ru.mail.krivonos.project_jd1.repository.ItemRepository;
import ru.mail.krivonos.project_jd1.repository.exceptions.ItemRepositoryException;
import ru.mail.krivonos.project_jd1.repository.model.Item;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    private static ItemRepository instance;

    private static final int LIMIT_VALUE = 9;

    private ItemRepositoryImpl() {
    }

    public static ItemRepository getInstance() {
        if (instance == null) {
            instance = new ItemRepositoryImpl();
        }
        return instance;
    }

    @Override
    public void add(Connection connection, Item item) throws ItemRepositoryException {
        String sql = "INSERT INTO Item (name, description, unique_number, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getUniqueNumber());
            preparedStatement.setBigDecimal(4, item.getPrice());
            int added = preparedStatement.executeUpdate();
            System.out.println("-------- " + added + " Item Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    @Override
    public void addItems(Connection connection, List<Item> items) throws ItemRepositoryException {
        String sql = "INSERT IGNORE INTO Item (name, description, unique_number, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            for (Item item : items) {
                preparedStatement.setString(1, item.getName());
                preparedStatement.setString(2, item.getDescription());
                preparedStatement.setString(3, item.getUniqueNumber());
                preparedStatement.setBigDecimal(4, item.getPrice());
                preparedStatement.addBatch();
            }
            int[] results = preparedStatement.executeBatch();
            int added = 0;
            for (int result : results) {
                added += result;
            }
            System.out.println("-------- " + added + " Items Added --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    @Override
    public Item findByUniqueNumber(Connection connection, String uniqueNumber) throws ItemRepositoryException {
        String sql = "SELECT id FROM Item WHERE unique_number = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, uniqueNumber);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Item item = new Item();
                    item.setId(resultSet.getLong("id"));
                    return item;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
        return null;
    }

    @Override
    public void deleteItemsWithLessPrice(Connection connection, BigDecimal priceBorder) throws ItemRepositoryException {
        String sql = "UPDATE Item SET deleted = TRUE WHERE price < ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, priceBorder);
            int deleted = preparedStatement.executeUpdate();
            System.out.println("-------- " + deleted + " Items Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    @Override
    public List<Item> findAll(Connection connection, Integer pageNumber) throws ItemRepositoryException {
        String sql = "SELECT * FROM Item WHERE deleted = FALSE LIMIT ? OFFSET ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, LIMIT_VALUE);
            preparedStatement.setInt(2, (pageNumber - 1) * LIMIT_VALUE);
            List<Item> items;
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                items = getItems(resultSet);
            }
            System.out.println("-------- " + items.size() + " Items Was Found --------");
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    @Override
    public Integer countPages(Connection connection) throws ItemRepositoryException {
        String sql = "SELECT COUNT(*) AS lines_number FROM Item";
        int pagesNumber = 0;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int lines_number = resultSet.getInt("lines_number");
                pagesNumber = lines_number / LIMIT_VALUE;
                if (lines_number % LIMIT_VALUE > 0) {
                    pagesNumber += 1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
        return pagesNumber;
    }

    @Override
    public List<Item> findItemsInPriceRange(Connection connection, BigDecimal rangeStart, BigDecimal rangeEnd,
                                            Integer pageNumber) throws ItemRepositoryException {
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
            return items;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    @Override
    public void deleteByID(Connection connection, Long id) throws ItemRepositoryException {
        String sql = "UPDATE Item SET deleted = TRUE WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            int deleted = preparedStatement.executeUpdate();
            System.out.println("-------- " + deleted + " Item Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    @Override
    public void updatePriceByID(Connection connection, Long id, BigDecimal price) throws ItemRepositoryException {
        String sql = "UPDATE Item SET price = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setBigDecimal(1, price);
            preparedStatement.setLong(2, id);
            int updated = preparedStatement.executeUpdate();
            System.out.println("-------- " + updated + " Item Deleted --------");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ItemRepositoryException(e);
        }
    }

    private List<Item> getItems(ResultSet resultSet) throws ItemRepositoryException {
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
            throw new ItemRepositoryException(e);
        }
    }
}
