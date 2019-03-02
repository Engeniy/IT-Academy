package ru.mail.krivonos.project_jd1.services.converter.order;

import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.services.converter.item.ItemForOrderConverter;
import ru.mail.krivonos.project_jd1.services.converter.item.ItemForOrderConverterImpl;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForCustomerDTO;

import java.math.BigDecimal;

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
        order.setState(orderForCustomerDTO.getState());
        return order;
    }

    @Override
    public OrderForCustomerDTO toDTO(Order order) {
        OrderForCustomerDTO orderForCustomerDTO = new OrderForCustomerDTO();
        orderForCustomerDTO.setItem(itemForOrderConverter.toDTO(order.getItem()));
        orderForCustomerDTO.setDateOfCreation(order.getDateOfCreation());
        orderForCustomerDTO.setQuantity(order.getQuantity());
        orderForCustomerDTO.setState(order.getState());
        BigDecimal itemPrice = order.getItem().getPrice();
        Integer itemQuantity = order.getQuantity();
        orderForCustomerDTO.setSum(itemPrice.multiply(BigDecimal.valueOf(itemQuantity)));
        return orderForCustomerDTO;
    }
}
