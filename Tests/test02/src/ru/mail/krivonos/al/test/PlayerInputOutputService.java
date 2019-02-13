package ru.mail.krivonos.al.test;

import java.util.List;

public interface PlayerInputOutputService {

    List<String> writePlayersToFile(String filePath, int collectionSize, int ageFrom, int ageTo, int threadsNumber);

    List<String> readPlayersNamesFromFile(List<String> fileNames, int ageFrom, int ageTo, boolean isActiveCondition);
}
