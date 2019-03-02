package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

import java.io.File;
import java.util.List;

public interface JAXBParserService {

    List<XMLItemDTO> parse(File file);

    boolean validate(File xsdFile, File xmlFile);
}
