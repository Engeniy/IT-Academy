package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface ItemService {

    void add(ItemDTO itemDTO);

    void deleteByID(Long id);

    void updatePriceByID(Long id, BigDecimal price);

    List<ItemDTO> getAll(Integer pageNumber);

    Integer countPages();

    void addItems(Collection<XMLItemDTO> collection);

    void deleteItemsWithLessPrice(BigDecimal priceBorder);

    void multiplyPricesFromRange(BigDecimal rangeStart, BigDecimal rangeEnd, Double multiplier, Integer pageNumber);
}
