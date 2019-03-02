package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.order.CreatedOrderDTO;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForCustomerDTO;
import ru.mail.krivonos.project_jd1.services.model.order.OrderForSaleDTO;

import java.util.List;

public interface OrderService {

    void add(CreatedOrderDTO orderDTO);

    void update(OrderForSaleDTO orderDTO);

    void delete(OrderForSaleDTO orderDTO);

    List<OrderForSaleDTO> getAll(Integer pageNumber);

    List<OrderForSaleDTO> getAllByOrderState(Integer pageNumber, String orderState);

    List<OrderForCustomerDTO> getAllForUser(Long userID, Integer PageNumber);
}
