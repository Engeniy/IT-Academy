package ru.mail.krivonos.project_jd1.services.impl;

import ru.mail.krivonos.project_jd1.repository.ItemRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.exceptions.ItemRepositoryException;
import ru.mail.krivonos.project_jd1.repository.impl.ItemRepositoryImpl;
import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.converter.ItemConverter;
import ru.mail.krivonos.project_jd1.services.converter.impl.ItemConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.xml.XMLItemConverterImpl;
import ru.mail.krivonos.project_jd1.services.exceptions.ItemUniqueNumberException;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.services.util.PageCountUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private static ItemService instance;

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private ItemRepository itemRepository = ItemRepositoryImpl.getInstance();

    private ItemConverter itemConverter = ItemConverterImpl.getInstance();

    private ItemServiceImpl() {
    }

    public static ItemService getInstance() {
        if (instance == null) {
            synchronized (ItemServiceImpl.class) {
                if (instance == null) {
                    instance = new ItemServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public void add(ItemDTO itemDTO) throws ItemUniqueNumberException {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Item item = ItemConverterImpl.getInstance().fromDTO(itemDTO);
                Item byUniqueNumber = itemRepository.findByUniqueNumber(connection, item.getUniqueNumber());
                if (byUniqueNumber != null) {
                    throw new ItemUniqueNumberException();
                }
                itemRepository.add(connection, item);
                connection.commit();
                System.out.println("SERVICE");
            } catch (SQLException | ItemRepositoryException e) {
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
    public void deleteByID(Long id) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                itemRepository.deleteByID(connection, id);
                connection.commit();
            } catch (SQLException | ItemRepositoryException e) {
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
    public List<ItemDTO> getAll(Integer pageNumber) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Item> items = itemRepository.findAll(connection, pageNumber);
                if (!items.isEmpty()) {
                    List<ItemDTO> itemsDTO = getItemsDTO(items);
                    connection.commit();
                    System.out.println("-------- " + items.size() + " Items Found --------");
                    return itemsDTO;
                }
                connection.commit();
            } catch (SQLException | ItemRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- No Items Found --------");
        return Collections.emptyList();
    }

    @Override
    public Integer countPages() {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                int linesNumber = itemRepository.countPages(connection);
                connection.commit();
                return PageCountUtil.countPages(linesNumber);
            } catch (SQLException | ItemRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public void addItems(Collection<XMLItemDTO> collection) throws ItemUniqueNumberException {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Item> items = new ArrayList<>();
                for (XMLItemDTO itemDTO : collection) {
                    Item item = XMLItemConverterImpl.getInstance().fromDTO(itemDTO);
                    if (!isUnique(connection, item.getUniqueNumber())) {
                        throw new ItemUniqueNumberException();
                    }
                    items.add(item);
                }
                itemRepository.addItems(connection, items);
                connection.commit();
            } catch (SQLException | ItemRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<ItemDTO> getItemsDTO(List<Item> items) {
        List<ItemDTO> itemDTOList = new ArrayList<>(items.size());
        for (Item item : items) {
            ItemDTO itemDTO = itemConverter.toDTO(item);
            itemDTOList.add(itemDTO);
        }
        return itemDTOList;
    }

    private boolean isUnique(Connection connection, String uniqueNumber) throws ItemRepositoryException {
        Item byUniqueNumber = itemRepository.findByUniqueNumber(connection, uniqueNumber);
        if (byUniqueNumber != null) {
            return false;
        }
        return true;
    }
}
