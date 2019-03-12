package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.services.converter.ItemForOrderConverter;
import ru.mail.krivonos.project_jd1.services.converter.OrderForSaleConverter;
import ru.mail.krivonos.project_jd1.services.converter.UserForOrderConverter;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;

public class OrderForSaleConverterImpl implements OrderForSaleConverter {

    private static OrderForSaleConverter instance;

    private ItemForOrderConverter itemForOrderConverter = ItemForOrderConverterImpl.getInstance();

    private UserForOrderConverter userForOrderConverter = UserForOrderConverterImpl.getInstance();

    private OrderForSaleConverterImpl() {
    }

    public static OrderForSaleConverter getInstance() {
        if (instance == null) {
            instance = new OrderForSaleConverterImpl();
        }
        return instance;
    }

    @Override
    public Order fromDTO(OrderForSaleDTO orderForSaleDTO) {
        Order order = new Order();
        order.setId(orderForSaleDTO.getId());
        order.setUser(userForOrderConverter.fromDTO(orderForSaleDTO.getUser()));
        order.setItem(itemForOrderConverter.fromDTO(orderForSaleDTO.getItem()));
        order.setDateOfCreation(orderForSaleDTO.getDateOfCreation());
        order.setQuantity(orderForSaleDTO.getQuantity());
        order.setState(OrderState.valueOf(orderForSaleDTO.getState()));
        return order;
    }

    @Override
    public OrderForSaleDTO toDTO(Order order) {
        OrderForSaleDTO orderForSaleDTO = new OrderForSaleDTO();
        orderForSaleDTO.setId(order.getId());
        orderForSaleDTO.setUser(userForOrderConverter.toDTO(order.getUser()));
        orderForSaleDTO.setItem(itemForOrderConverter.toDTO(order.getItem()));
        orderForSaleDTO.setDateOfCreation(order.getDateOfCreation());
        orderForSaleDTO.setQuantity(order.getQuantity());
        orderForSaleDTO.setState(order.getState().name());
        return orderForSaleDTO;
    }
}
