package ru.mail.krivonos.al.project_jd1.services.converter;

import ru.mail.krivonos.al.project_jd1.repository.model.Item;
import ru.mail.krivonos.al.project_jd1.services.model.ItemDTO;

public interface ItemConverter {

    Item fromDTO(ItemDTO itemDTO);

    ItemDTO toDTO(Item item);
}
