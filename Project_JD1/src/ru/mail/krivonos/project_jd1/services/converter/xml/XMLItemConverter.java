package ru.mail.krivonos.project_jd1.services.converter.xml;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

public interface XMLItemConverter {

    Item fromDTO(XMLItemDTO xmlItemDTO);

    XMLItemDTO toDTO(Item item);
}
