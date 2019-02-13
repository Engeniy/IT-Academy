package ru.mail.krivonos.al.test.app;

import ru.mail.krivonos.al.test.*;
import ru.mail.krivonos.al.test.impl.ConsoleServiceImpl;
import ru.mail.krivonos.al.test.impl.OutputServiceImpl;
import ru.mail.krivonos.al.test.impl.PlayerInputOutputServiceImpl;
import ru.mail.krivonos.al.test.impl.XMLParserFactoryImpl;
import ru.mail.krivonos.al.test.model.ParserType;

import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String phrase = "In any programming language arrays are used that are convenient " +
                "for working with large number of the same type of data";
        int arraySize = 4;
        String fileForArrays = "src/ru/mail/krivonos/al/test/arrays.txt";
        ConsoleService consoleService = ConsoleServiceImpl.getInstance();
        int threadsNumber = consoleService.getNumber("Input threads number:");
        OutputService outputService = OutputServiceImpl.getInstance();
        outputService.outputArray(fileForArrays, arraySize, phrase, threadsNumber);

        String filePath = "src/ru/mail/krivonos/al/test";
        PlayerInputOutputService inputOutputService = PlayerInputOutputServiceImpl.getInstance();
        List<String> fileNames = inputOutputService
                .writePlayersToFile(filePath, 10, 20, 40, 5);
        List<String> playerNames = inputOutputService
                .readPlayersNamesFromFile(fileNames, 25, 30, true);
        for (String playerName : playerNames) {
            System.out.print(playerName + " ");
        }

        String xmlPath = "src/ru/mail/krivonos/al/test/resource/myxml.xml";
        String xsdPath = "src/ru/mail/krivonos/al/test/resource/myschema.xsd";
        XMLParserFactory factory = XMLParserFactoryImpl.getInstance();
        factory.validate(xsdPath, xmlPath);
        File xmlDocument = new File(xmlPath);

        XMLParserService parserService = factory.getParser(ParserType.DOM);
        String domParsed = parserService.parse(xmlDocument);
        System.out.println(domParsed);

        parserService = factory.getParser(ParserType.SAX);
        String saxParsed = parserService.parse(xmlDocument);
        System.out.println(saxParsed);

        parserService = factory.getParser(ParserType.JAXB);
        String jaxbParsed = parserService.parse(xmlDocument);
        System.out.println(jaxbParsed);

        parserService = factory.getParser(ParserType.StAX);
        String staxParsed = parserService.parse(xmlDocument);
        System.out.println(staxParsed);

    }
}
