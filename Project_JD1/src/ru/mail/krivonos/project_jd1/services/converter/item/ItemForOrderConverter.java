package ru.mail.krivonos.project_jd1.services.converter.item;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.model.item.ItemForOrderDTO;

public interface ItemForOrderConverter {

    Item fromDTO(ItemForOrderDTO itemForOrderDTO);

    ItemForOrderDTO toDTO(Item item);
}
