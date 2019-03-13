package ru.mail.krivonos.project_jd1.servlets.constants;

import java.io.File;

public class ServletConstants {

    public static final String SESSION_USER_KEY = "user-session";
    public static final String DEFAULT_URL = "/dispatcher?command=";
    public static final String MESSAGE_POSTFIX = "&message=";
    public static final String ERROR_POSTFIX = "&error=";
    public static final String XSD_PATH = File.separator + "resources" + File.separator + "itemschema.xsd";

    private ServletConstants() {
    }
}
