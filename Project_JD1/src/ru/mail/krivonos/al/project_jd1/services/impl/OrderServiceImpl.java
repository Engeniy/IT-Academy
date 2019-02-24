package ru.mail.krivonos.al.project_jd1.services.impl;

import ru.mail.krivonos.al.project_jd1.repository.OrderRepository;
import ru.mail.krivonos.al.project_jd1.repository.connection.ConnectionService;
import ru.mail.krivonos.al.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.al.project_jd1.repository.impl.OrderRepositoryImpl;
import ru.mail.krivonos.al.project_jd1.repository.model.Order;
import ru.mail.krivonos.al.project_jd1.services.OrderService;
import ru.mail.krivonos.al.project_jd1.services.converter.OrderConverterImpl;
import ru.mail.krivonos.al.project_jd1.services.model.OrderDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static OrderService instance;

    private ConnectionService connectionService = ConnectionServiceImpl.getInstance();

    private OrderRepository orderRepository = OrderRepositoryImpl.getInstance();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderServiceImpl();
        }
        return instance;
    }

    @Override
    public void add(OrderDTO orderDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Order order = OrderConverterImpl.getInstance().fromDTO(orderDTO);
                orderRepository.addOrder(connection, order);
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
    public void update(OrderDTO orderDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Order order = OrderConverterImpl.getInstance().fromDTO(orderDTO);
                orderRepository.updateState(connection, order);
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
    public void delete(OrderDTO orderDTO) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                Order order = OrderConverterImpl.getInstance().fromDTO(orderDTO);
                orderRepository.delete(connection, order);
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
    public List<OrderDTO> getAll(Integer pageNumber) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Order> orders = orderRepository.findAll(connection, pageNumber);
                if (orders.isEmpty()) {
                    return null;
                }
                List<OrderDTO> orderDTOList = getOrderDTOList(orders);
                connection.commit();
                return orderDTOList;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<OrderDTO> getAllByUserID(Long userID, Integer pageNumber) {
        try (Connection connection = connectionService.getConnection()) {
            try {
                connection.setAutoCommit(false);
                List<Order> orders = orderRepository.findAllByUserID(connection, userID, pageNumber);
                if (orders.isEmpty()) {
                    return null;
                }
                List<OrderDTO> orderDTOList = getOrderDTOList(orders);
                connection.commit();
                return orderDTOList;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                connection.rollback();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return null;
    }

    private List<OrderDTO> getOrderDTOList(List<Order> orders) {
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = OrderConverterImpl.getInstance().toDTO(order);
            orderDTOList.add(orderDTO);
        }
        return orderDTOList;
    }
}
