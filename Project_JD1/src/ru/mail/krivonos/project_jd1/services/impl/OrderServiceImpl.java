package ru.mail.krivonos.project_jd1.services.impl;

import ru.mail.krivonos.project_jd1.repository.OrderRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.exceptions.OrderRepositoryException;
import ru.mail.krivonos.project_jd1.repository.impl.OrderRepositoryImpl;
import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.services.OrderService;
import ru.mail.krivonos.project_jd1.services.converter.OrderForCustomerConverter;
import ru.mail.krivonos.project_jd1.services.converter.OrderForSaleConverter;
import ru.mail.krivonos.project_jd1.services.converter.impl.CreatedOrderConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.impl.OrderForCustomerConverterImpl;
import ru.mail.krivonos.project_jd1.services.converter.impl.OrderForSaleConverterImpl;
import ru.mail.krivonos.project_jd1.services.model.order.CreatedOrderDTO;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForCustomerDTO;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;
import ru.mail.krivonos.project_jd1.services.util.PageCountUtil;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static OrderService instance;

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private OrderRepository orderRepository = OrderRepositoryImpl.getInstance();

    private OrderForCustomerConverter orderForCustomerConverter = OrderForCustomerConverterImpl.getInstance();

    private OrderForSaleConverter orderForSaleConverter = OrderForSaleConverterImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(CreatedOrderDTO orderDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Order order = CreatedOrderConverterImpl.getInstance().fromDTO(orderDTO);
                order.setDateOfCreation(new Date());
                orderRepository.add(connection, order);
                connection.commit();
            } catch (SQLException | OrderRepositoryException e) {
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
    public Integer countPages() {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Integer linesNumber = orderRepository.countPages(connection);
                connection.commit();
                return PageCountUtil.countPages(linesNumber);
            } catch (SQLException | OrderRepositoryException e) {
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
    public Integer countPagesForUser(Long id) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Integer linesNumber = orderRepository.countPagesForUser(connection, id);
                connection.commit();
                return PageCountUtil.countPages(linesNumber);
            } catch (SQLException | OrderRepositoryException e) {
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
    public Integer countPagesForState(String state) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                OrderState orderState = OrderState.valueOf(state);
                Integer linesNumber = orderRepository.countPagesForState(connection, orderState);
                connection.commit();
                return PageCountUtil.countPages(linesNumber);
            } catch (SQLException | OrderRepositoryException e) {
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
    public void update(Long id, String newState) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                OrderState state = OrderState.valueOf(newState);
                orderRepository.updateState(connection, id, state);
                connection.commit();
            } catch (SQLException | OrderRepositoryException e) {
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
    public void delete(OrderForSaleDTO orderDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Order order = OrderForSaleConverterImpl.getInstance().fromDTO(orderDTO);
                orderRepository.delete(connection, order);
                connection.commit();
            } catch (SQLException | OrderRepositoryException e) {
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
    public List<OrderForSaleDTO> getAll(Integer pageNumber) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Order> orders = orderRepository.findAll(connection, pageNumber);
                List<OrderForSaleDTO> orderForSaleDTOList = extractOrders(connection, orders);
                if (orderForSaleDTOList != null) return orderForSaleDTOList;
            } catch (SQLException | OrderRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- No Orders Was Found --------");
        return Collections.emptyList();
    }

    @Override
    public List<OrderForSaleDTO> getAllByOrderState(Integer pageNumber, String orderState) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                OrderState state = OrderState.valueOf(orderState);
                List<Order> orders = orderRepository.findAllByOrderState(connection, pageNumber, state);
                List<OrderForSaleDTO> orderForSaleDTOList = extractOrders(connection, orders);
                if (orderForSaleDTOList != null) return orderForSaleDTOList;
            } catch (SQLException | OrderRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- No Orders Was Found --------");
        return Collections.emptyList();
    }

    @Override
    public List<OrderForCustomerDTO> getAllForUser(Long userID, Integer pageNumber) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Order> orders = orderRepository.findAllByUserID(connection, userID, pageNumber);
                if (!orders.isEmpty()) {
                    List<OrderForCustomerDTO> orderDTOList = getOrderForCustomerDTOs(orders);
                    connection.commit();
                    System.out.println("-------- " + orderDTOList.size() + " Orders Selected --------");
                    return orderDTOList;
                }
                connection.commit();
            } catch (SQLException | OrderRepositoryException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        System.out.println("-------- No Orders Was Found --------");
        return Collections.emptyList();
    }

    private List<OrderForSaleDTO> extractOrders(Connection connection, List<Order> orders) throws SQLException {
        if (!orders.isEmpty()) {
            List<OrderForSaleDTO> orderForSaleDTOList = getOrderForSaleDTOs(orders);
            connection.commit();
            System.out.println("-------- " + orderForSaleDTOList.size() + " Orders Selected --------");
            return orderForSaleDTOList;
        }
        connection.commit();
        return null;
    }

    private List<OrderForSaleDTO> getOrderForSaleDTOs(List<Order> orders) {
        List<OrderForSaleDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            OrderForSaleDTO orderDTO = orderForSaleConverter.toDTO(order);
            orderDTO.setSum(getSum(order));
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    private List<OrderForCustomerDTO> getOrderForCustomerDTOs(List<Order> orders) {
        List<OrderForCustomerDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            OrderForCustomerDTO orderDTO = orderForCustomerConverter.toDTO(order);
            orderDTO.setSum(getSum(order));
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }

    private BigDecimal getSum(Order order) {
        BigDecimal itemPrice = order.getItem().getPrice();
        Integer itemQuantity = order.getQuantity();
        return itemPrice.multiply(BigDecimal.valueOf(itemQuantity));
    }
}
