package ru.mail.krivonos.project_jd1.services.converter.item;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;

public class ItemConverterImpl implements ItemConverter {

    private static ItemConverter instance;

    private ItemConverterImpl() {
    }

    public static ItemConverter getInstance() {
        if (instance == null) {
            instance = new ItemConverterImpl();
        }
        return instance;
    }

    @Override
    public Item fromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setUniqueNumber(itemDTO.getUniqueNumber());
        item.setPrice(itemDTO.getPrice());
        return item;
    }

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setUniqueNumber(item.getUniqueNumber());
        itemDTO.setPrice(item.getPrice());
        return itemDTO;
    }
}
