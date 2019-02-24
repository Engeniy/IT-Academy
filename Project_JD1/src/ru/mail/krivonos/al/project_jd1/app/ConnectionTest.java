package ru.mail.krivonos.al.project_jd1.app;

import ru.mail.krivonos.al.project_jd1.repository.model.Item;
import ru.mail.krivonos.al.project_jd1.services.DatabaseInitService;
import ru.mail.krivonos.al.project_jd1.services.ItemService;
import ru.mail.krivonos.al.project_jd1.services.RandomService;
import ru.mail.krivonos.al.project_jd1.services.impl.DatabaseInitServiceImpl;
import ru.mail.krivonos.al.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.al.project_jd1.services.impl.RandomServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class ConnectionTest {
    public static void main(String[] args) {
        DatabaseInitService databaseInitService = DatabaseInitServiceImpl.getInstance();
        databaseInitService.initDatabase();
        RandomService randomService = RandomServiceImpl.getInstance();
        /*List<Item> items = randomService.getItemList(10, 30, 3000);
        ItemService itemService = ItemServiceImpl.getInstance();
        itemService.addItems(items);
        BigDecimal rangeStart = BigDecimal.valueOf(1000);
        BigDecimal rangeEnd = BigDecimal.valueOf(1500);
        itemService.multiplyPricesFromRange(rangeStart, rangeEnd, 1.2);
        BigDecimal priceBorder = BigDecimal.valueOf(700);
        itemService.deleteItemsWithLessPrice(priceBorder);*/
    }
}