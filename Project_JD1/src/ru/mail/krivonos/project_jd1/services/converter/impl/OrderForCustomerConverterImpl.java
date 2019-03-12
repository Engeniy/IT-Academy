package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.services.converter.ItemForOrderConverter;
import ru.mail.krivonos.project_jd1.services.converter.OrderForCustomerConverter;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForCustomerDTO;

public class OrderForCustomerConverterImpl implements OrderForCustomerConverter {

    private static OrderForCustomerConverter instance;

    private ItemForOrderConverter itemForOrderConverter = ItemForOrderConverterImpl.getInstance();

    private OrderForCustomerConverterImpl() {
    }

    public static OrderForCustomerConverter getInstance() {
        if (instance == null) {
            instance = new OrderForCustomerConverterImpl();
        }
        return instance;
    }

    @Override
    public Order fromDTO(OrderForCustomerDTO orderForCustomerDTO) {
        Order order = new Order();
        order.setItem(itemForOrderConverter.fromDTO(orderForCustomerDTO.getItem()));
        order.setDateOfCreation(orderForCustomerDTO.getDateOfCreation());
        order.setQuantity(orderForCustomerDTO.getQuantity());
        order.setState(OrderState.valueOf(orderForCustomerDTO.getState()));
        return order;
    }

    @Override
    public OrderForCustomerDTO toDTO(Order order) {
        OrderForCustomerDTO orderForCustomerDTO = new OrderForCustomerDTO();
        orderForCustomerDTO.setItem(itemForOrderConverter.toDTO(order.getItem()));
        orderForCustomerDTO.setDateOfCreation(order.getDateOfCreation());
        orderForCustomerDTO.setQuantity(order.getQuantity());
        orderForCustomerDTO.setState(order.getState().name());
        return orderForCustomerDTO;
    }
}
