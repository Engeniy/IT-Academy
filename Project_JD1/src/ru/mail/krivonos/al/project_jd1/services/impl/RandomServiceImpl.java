package ru.mail.krivonos.al.project_jd1.services.impl;

import ru.mail.krivonos.al.project_jd1.repository.model.Item;
import ru.mail.krivonos.al.project_jd1.services.RandomService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomServiceImpl implements RandomService {

    private Random random = new Random();

    private static RandomService instance;

    private RandomServiceImpl() {
    }

    public static RandomService getInstance() {
        if (instance == null) {
            instance = new RandomServiceImpl();
        }
        return instance;
    }

    @Override
    public Item getItem(int rangeStart, int rangeEnd) {
        Item item = new Item();
        String name = "Test" + random.nextInt(100);
        String description = "Description of " + name;
        String uniqueNumber = UUID.randomUUID().toString();
        Double priceBuffer = random.nextInt(rangeEnd - rangeStart) + rangeStart + random.nextDouble();
        BigDecimal price = convert(priceBuffer);
        item.setName(name);
        item.setDescription(description);
        item.setUniqueNumber(uniqueNumber);
        item.setPrice(price);
        return item;
    }

    @Override
    public List<Item> getItemList(int size, int rangeStart, int rangeEnd) {
        List<Item> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Item item = getItem(rangeStart, rangeEnd);
            items.add(item);
        }
        return items;
    }

    private BigDecimal convert(Double price) {
        price = price * 100;
        price = Math.round(price) * 1d;
        price = price / 100d;
        BigDecimal result = BigDecimal.valueOf(price);
        return result;
    }
}
