package ru.mail.krivonos.al.additional.twentyone.testpractice;

import ru.mail.krivonos.al.additional.twentyone.testpractice.model.ParserType;

public interface XMLService {

    void validate(String xsdFileName, String xmlFileName);

    void printItemInfo(ParserType type, String xmlFileName);
}
