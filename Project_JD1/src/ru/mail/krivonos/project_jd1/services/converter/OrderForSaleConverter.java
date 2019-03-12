package ru.mail.krivonos.project_jd1.services.converter;

import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;

public interface OrderForSaleConverter {

    Order fromDTO(OrderForSaleDTO orderForSaleDTO);

    OrderForSaleDTO toDTO(Order order);
}
