package ru.mail.krivonos.project_jd1.services.converter.impl;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.converter.ItemConverter;
import ru.mail.krivonos.project_jd1.services.model.item.ItemDTO;

import java.math.BigDecimal;

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
        item.setPrice(new BigDecimal(itemDTO.getPrice()));
        return item;
    }

    @Override
    public ItemDTO toDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setUniqueNumber(item.getUniqueNumber());
        itemDTO.setPrice(item.getPrice().toString());
        return itemDTO;
    }
}
