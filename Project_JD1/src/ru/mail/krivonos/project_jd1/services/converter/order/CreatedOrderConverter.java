package ru.mail.krivonos.project_jd1.services.converter.order;

import ru.mail.krivonos.project_jd1.repository.model.Order;
import ru.mail.krivonos.project_jd1.services.model.order.CreatedOrderDTO;

public interface CreatedOrderConverter {

    Order fromDTO(CreatedOrderDTO orderDTO);

    CreatedOrderDTO toDTO(Order order);
}
