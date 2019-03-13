package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

import java.io.InputStream;
import java.util.List;

public interface ItemsUploadService {

    List<XMLItemDTO> parse(InputStream fileContent);
}
