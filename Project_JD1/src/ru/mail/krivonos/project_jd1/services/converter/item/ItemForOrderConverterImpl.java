package ru.mail.krivonos.project_jd1.services.converter.item;

import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.model.item.ItemForOrderDTO;

public class ItemForOrderConverterImpl implements ItemForOrderConverter {

    private static ItemForOrderConverter instance;

    private ItemForOrderConverterImpl() {
    }

    public static ItemForOrderConverter getInstance() {
        if (instance == null) {
            instance = new ItemForOrderConverterImpl();
        }
        return instance;
    }

    @Override
    public Item fromDTO(ItemForOrderDTO itemForOrderDTO) {
        Item item = new Item();
        item.setName(itemForOrderDTO.getName());
        item.setUniqueNumber(itemForOrderDTO.getUniqueNumber());
        item.setPrice(itemForOrderDTO.getPrice());
        return item;
    }

    @Override
    public ItemForOrderDTO toDTO(Item item) {
        ItemForOrderDTO itemForOrderDTO = new ItemForOrderDTO();
        itemForOrderDTO.setName(item.getName());
        itemForOrderDTO.setUniqueNumber(item.getUniqueNumber());
        itemForOrderDTO.setPrice(item.getPrice());
        return itemForOrderDTO;
    }
}
