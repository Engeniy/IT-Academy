package ru.mail.krivonos.al.project_jd1.services.impl;

import ru.mail.krivonos.al.project_jd1.repository.ItemRepository;
import ru.mail.krivonos.al.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.al.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.al.project_jd1.repository.impl.ItemRepositoryImpl;
import ru.mail.krivonos.al.project_jd1.repository.model.Item;
import ru.mail.krivonos.al.project_jd1.services.ItemService;
import ru.mail.krivonos.al.project_jd1.services.converter.ItemConverterImpl;
import ru.mail.krivonos.al.project_jd1.services.model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static ItemService instance;

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private ItemRepository itemRepository = ItemRepositoryImpl.getInstance();

    private ItemServiceImpl() {
    }

    public static ItemService getInstance() {
        if (instance == null) {
            instance = new ItemServiceImpl();
        }
        return instance;
    }

    @Override
    public void addItem(ItemDTO itemDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Item item = ItemConverterImpl.getInstance().fromDTO(itemDTO);
                itemRepository.addItem(connection, item);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addItems(Collection<ItemDTO> collection) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                for (ItemDTO itemDTO : collection) {
                    Item item = ItemConverterImpl.getInstance().fromDTO(itemDTO);
                    itemRepository.addItem(connection, item);
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteItemsWithLessPrice(BigDecimal priceBorder) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                itemRepository.deleteItemsWithLessPrice(connection, priceBorder);
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void multiplyPricesFromRange(BigDecimal rangeStart, BigDecimal rangeEnd, Double multiplier,
                                        Integer pageNumber) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Item> items = itemRepository.findItemsInPriceRange(connection, rangeStart, rangeEnd, pageNumber);
                if (items != null) {
                    for (Item item : items) {
                        BigDecimal price = item.getPrice().multiply(BigDecimal.valueOf(multiplier));
                        itemRepository.updatePriceByID(connection, item.getId(), price);
                    }
                }
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
