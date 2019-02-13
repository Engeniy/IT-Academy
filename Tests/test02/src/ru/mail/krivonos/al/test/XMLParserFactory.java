package ru.mail.krivonos.al.test;

import ru.mail.krivonos.al.test.model.ParserType;

public interface XMLParserFactory {

    XMLParserService getParser(ParserType type);

    void validate(String xsdFileName, String xmlFileName);
}
