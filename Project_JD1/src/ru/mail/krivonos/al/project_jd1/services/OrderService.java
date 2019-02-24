package ru.mail.krivonos.al.project_jd1.services;

import ru.mail.krivonos.al.project_jd1.services.model.OrderDTO;

import java.util.List;

public interface OrderService {

    void add(OrderDTO orderDTO);

    void update(OrderDTO orderDTO);

    void delete(OrderDTO orderDTO);

    List<OrderDTO> getAll(Integer pageNumber);

    List<OrderDTO> getAllByUserID(Long userID, Integer PageNumber);
}
