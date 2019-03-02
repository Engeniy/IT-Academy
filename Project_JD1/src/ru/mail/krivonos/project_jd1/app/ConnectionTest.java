package ru.mail.krivonos.project_jd1.app;

import ru.mail.krivonos.project_jd1.repository.ItemRepository;
import ru.mail.krivonos.project_jd1.repository.connection.ConnectionServiceImpl;
import ru.mail.krivonos.project_jd1.repository.exceptions.ItemRepositoryException;
import ru.mail.krivonos.project_jd1.repository.impl.ItemRepositoryImpl;
import ru.mail.krivonos.project_jd1.repository.model.Item;
import ru.mail.krivonos.project_jd1.services.DatabaseInitService;
import ru.mail.krivonos.project_jd1.services.ItemService;
import ru.mail.krivonos.project_jd1.services.JAXBParserService;
import ru.mail.krivonos.project_jd1.services.RandomService;
import ru.mail.krivonos.project_jd1.services.impl.DatabaseInitServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.ItemServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.RandomServiceImpl;
import ru.mail.krivonos.project_jd1.services.impl.xml.JAXBParserServiceImpl;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ConnectionTest {
    public static void main(String[] args) {
        DatabaseInitService databaseInitService = DatabaseInitServiceImpl.getInstance();
        databaseInitService.initDatabase();
        RandomService randomService = RandomServiceImpl.getInstance();
        List<Item> items = randomService.getItemList(10, 30, 3000);
        ItemRepository itemRepository = ItemRepositoryImpl.getInstance();
        try (Connection connection = ConnectionServiceImpl.getInstance().getConnection()) {
            for (Item item : items) {
                itemRepository.add(connection, item);
            }
        } catch (SQLException | ItemRepositoryException e) {
            e.printStackTrace();
        }


//        File xsdFile = new File("src/ru/mail/krivonos/project_jd1/resources/itemschema.xsd");
//        File xmlFile = new File("src/ru/mail/krivonos/project_jd1/resources/inititems.xml");
//        JAXBParserService jaxbParserService = JAXBParserServiceImpl.getInstance();
//        System.out.println(jaxbParserService.validate(xsdFile, xmlFile));
//        System.out.println(jaxbParserService.parse(xmlFile));
    }
}