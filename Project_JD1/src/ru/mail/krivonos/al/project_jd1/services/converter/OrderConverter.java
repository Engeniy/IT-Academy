package ru.mail.krivonos.al.project_jd1.services.converter;

import ru.mail.krivonos.al.project_jd1.repository.model.Order;
import ru.mail.krivonos.al.project_jd1.services.model.OrderDTO;

public interface OrderConverter {

    Order fromDTO(OrderDTO orderDTO);

    OrderDTO toDTO(Order order);
}
