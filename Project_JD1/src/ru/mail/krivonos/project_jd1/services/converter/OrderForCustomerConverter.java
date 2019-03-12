package ru.mail.krivonos.project_jd1.services.converter;

import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForCustomerDTO;

public interface OrderForCustomerConverter {

    Order fromDTO(OrderForCustomerDTO orderForCustomerDTO);

    OrderForCustomerDTO toDTO(Order order);
}
