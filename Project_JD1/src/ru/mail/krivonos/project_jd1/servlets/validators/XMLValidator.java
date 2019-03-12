package ru.mail.krivonos.project_jd1.servlets.validators;

import java.io.File;
import java.io.InputStream;

public interface XMLValidator {
    boolean validate(File schemaFile, InputStream fileContent);
}
