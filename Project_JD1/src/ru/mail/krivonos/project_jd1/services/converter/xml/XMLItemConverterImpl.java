package ru.mail.krivonos.project_jd1.services.converter.xml;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

public class XMLItemConverterImpl implements XMLItemConverter {

    private static XMLItemConverter instance;

    private XMLItemConverterImpl(){
    }

    public static XMLItemConverter getInstance() {
        if (instance == null) {
            instance = new XMLItemConverterImpl();
        }
        return instance;
    }

    @Override
    public Item fromDTO(XMLItemDTO xmlItemDTO) {
        Item item = new Item();
        item.setName(xmlItemDTO.getName());
        item.setDescription(xmlItemDTO.getDescription());
        item.setPrice(xmlItemDTO.getPrice());
        item.setUniqueNumber(xmlItemDTO.getUniqueNumber());
        return item;
    }

    @Override
    public XMLItemDTO toDTO(Item item) {
        throw new UnsupportedOperationException();
    }
}
