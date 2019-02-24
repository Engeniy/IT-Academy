package ru.mail.krivonos.al.project_jd1.services;

import ru.mail.krivonos.al.project_jd1.repository.model.Item;
import ru.mail.krivonos.al.project_jd1.services.model.ItemDTO;

import java.math.BigDecimal;
import java.util.Collection;

public interface ItemService {

    void addItem(ItemDTO itemDTO);

    void addItems(Collection<ItemDTO> collection);

    void deleteItemsWithLessPrice(BigDecimal priceBorder);

    void multiplyPricesFromRange(BigDecimal rangeStart, BigDecimal rangeEnd, Double multiplier);
}
