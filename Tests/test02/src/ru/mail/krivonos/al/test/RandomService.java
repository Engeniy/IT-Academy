package ru.mail.krivonos.al.test;

import ru.mail.krivonos.al.test.model.Player;

import java.util.List;

public interface RandomService {

    int getNumber(int rangeStart, int rangeEnd);

    String[] getRandomWordsArray(int size, String phrase);

    List<Player> getPlayersList(int size, int ageFrom, int ageTo);

    int[] getArray(int size, int range);
}
