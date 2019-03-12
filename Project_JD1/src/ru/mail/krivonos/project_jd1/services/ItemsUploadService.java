package ru.mail.krivonos.project_jd1.services;

import ru.mail.krivonos.project_jd1.services.model.xml.XMLItemDTO;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ItemsUploadService {

    File uploadFile(Part part, String tempFileName) throws IOException;

    List<XMLItemDTO> parse(InputStream fileContent);

    boolean isValid(File schemaFile, InputStream fileContent);
}
