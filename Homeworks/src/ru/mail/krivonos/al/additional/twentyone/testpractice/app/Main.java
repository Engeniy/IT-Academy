package ru.mail.krivonos.al.additional.twentyone.testpractice.app;

import ru.mail.krivonos.al.additional.twentyone.testpractice.WritingReadingTaskService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.XMLService;
import ru.mail.krivonos.al.additional.twentyone.testpractice.impl.WritingReadingTaskServiceImpl;
import ru.mail.krivonos.al.additional.twentyone.testpractice.impl.XMLServiceImpl;
import ru.mail.krivonos.al.additional.twentyone.testpractice.model.ParserType;

public class Main {

    public static void main(String[] args) {
        String filePath = "src/ru/mail/krivonos/al/additional/twentyone/testpractice";
        WritingReadingTaskService writingReadingTaskService = WritingReadingTaskServiceImpl.getInstance();
        writingReadingTaskService.executeTasks(5, 10, 50, filePath);

        writingReadingTaskService.executeWriteReadTasks(5, 10, 20, filePath);

        XMLService xmlService = XMLServiceImpl.getInstance();
        String xsdFileName = "src/ru/mail/krivonos/al/additional/twentyone/testpractice/resource/myschema.xsd";
        String xmlFileName = "src/ru/mail/krivonos/al/additional/twentyone/testpractice/resource/myxml.xml";
        xmlService.validate(xsdFileName, xmlFileName);

        xmlService.printItemInfo(ParserType.DOM, xmlFileName);
        xmlService.printItemInfo(ParserType.SAX, xmlFileName);
        xmlService.printItemInfo(ParserType.JAXB, xmlFileName);
        xmlService.printItemInfo(ParserType.StAX, xmlFileName);
    }
}
