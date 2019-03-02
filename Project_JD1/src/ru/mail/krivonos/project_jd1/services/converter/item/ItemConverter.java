package ru.mail.krivonos.project_jd1.services.converter.item;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;

public interface ItemConverter {

    Item fromDTO(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);
}
