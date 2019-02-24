package ru.mail.krivonos.al.project_jd1.services.converter;

import ru.mail.krivonos.al.project_jd1.repository.model.Order;
import ru.mail.krivonos.al.project_jd1.services.model.OrderDTO;

import java.math.BigDecimal;

public class OrderConverterImpl implements OrderConverter {

    private static OrderConverter instance;

    private OrderConverterImpl() {
    }

    public static OrderConverter getInstance() {
        if (instance == null) {
            instance = new OrderConverterImpl();
        }
        return instance;
    }

    @Override
    public Order fromDTO(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(orderDTO.getUser());
        order.setItem(orderDTO.getItem());
        order.setCreationDate(orderDTO.getCreationDate());
        order.setQuantity(orderDTO.getQuantity());
        order.setState(orderDTO.getState());
        return order;
    }

    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser(order.getUser());
        orderDTO.setItem(order.getItem());
        orderDTO.setCreationDate(order.getCreationDate());
        orderDTO.setQuantity(order.getQuantity());
        orderDTO.setState(order.getState());
        BigDecimal sum = order.getItem().getPrice().multiply(BigDecimal.valueOf(order.getQuantity()));
        orderDTO.setSum(sum);
        return orderDTO;
    }
}
