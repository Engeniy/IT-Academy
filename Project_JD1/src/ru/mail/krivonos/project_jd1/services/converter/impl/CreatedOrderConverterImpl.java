package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.repository.model.OrderState;
import ru.mail.krivonos.project_jd1.repository.model.User;
import ru.mail.krivonos.project_jd1.services.converter.CreatedOrderConverter;
import ru.mail.krivonos.project_jd1.services.model.order.CreatedOrderDTO;

public class CreatedOrderConverterImpl implements CreatedOrderConverter {

    private static CreatedOrderConverter instance;

    private CreatedOrderConverterImpl() {
    }

    public static CreatedOrderConverter getInstance() {
        if (instance == null) {
            synchronized (CreatedOrderConverterImpl.class) {
                if (instance == null) {
                    instance = new CreatedOrderConverterImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public Order fromDTO(CreatedOrderDTO orderDTO) {
        Order order = new Order();
        User user = new User();
        user.setId(orderDTO.getUserID());
        order.setUser(user);
        Item item = new Item();
        item.setId(orderDTO.getItemID());
        order.setItem(item);
        order.setQuantity(orderDTO.getQuantity());
        order.setState(OrderState.NEW);
        return order;
    }

    @Override
    public CreatedOrderDTO toDTO(Order order) {
        throw new UnsupportedOperationException();
    }
}
