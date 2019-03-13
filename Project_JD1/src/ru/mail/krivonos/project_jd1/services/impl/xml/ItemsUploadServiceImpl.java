package ru.mail.krivonos.project_jd1.services.impl.xml;

import ru.mail.krivonos.project_jd1.services.ItemsUploadService;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;
import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemsDTO;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.List;

public class ItemsUploadServiceImpl implements ItemsUploadService {

    private static ItemsUploadService instance;

    private ItemsUploadServiceImpl() {
    }

    public static ItemsUploadService getInstance() {
        if (instance == null) {
            synchronized (ItemsUploadServiceImpl.class) {
                if (instance == null) {
                    instance = new ItemsUploadServiceImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public List<XMLItemDTO> parse(InputStream fileContent) {
        try {
            JAXBContext context = JAXBContext.newInstance(XMLItemsDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            XMLItemsDTO itemsDTO = (XMLItemsDTO) unmarshaller.unmarshal(fileContent);
            return itemsDTO.getXmlItemDTOList();
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
