package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ItemsUploadService {

    File uploadFile(Part part, String tempFileName) throws IOException;

    List<XMLItemDTO> parse(File file);

    boolean validate(File xsdFile, File xmlFile);
}
