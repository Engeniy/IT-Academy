package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.RandomService;
import ru.mail.krivonos.al.test.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomServiceImpl implements RandomService {

    private static RandomService instance;
    private Random random = new Random();

    @Override
    public int getNumber(int rangeStart, int rangeEnd) {
        return random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
    }

    @Override
    public String[] getRandomWordsArray(int size, String phrase) {
        String[] words = phrase.split(" ");
        String[] output = new String[size];
        for (int i = 0; i < output.length; i++) {
            int index = getNumber(0, words.length - 1);
            output[i] = words[index];
        }
        return output;
    }

    @Override
    public List<Player> getPlayersList(int size, int ageFrom, int ageTo) {
        List<Player> players = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int age = getNumber(ageFrom, ageTo);
            Player player = new Player("Test" + i, age, random.nextBoolean());
            players.add(player);
        }
        return players;
    }

    @Override
    public int[] getArray(int size, int range) {
        return new int[0];
    }

    private RandomServiceImpl() {
    }

    public static RandomService getInstance() {
        if (instance == null) {
            synchronized (RandomServiceImpl.class) {
                if (instance == null) {
                    instance = new RandomServiceImpl();
                }
            }
        }
        return instance;
    }
}
