package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.PlayerInputOutputService;
import ru.mail.krivonos.al.test.RandomService;
import ru.mail.krivonos.al.test.model.Player;
import ru.mail.krivonos.al.test.task.PlayerGeneratorTask;
import ru.mail.krivonos.al.test.task.PlayerReaderTask;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PlayerInputOutputServiceImpl implements PlayerInputOutputService {

    private static PlayerInputOutputService instance;
    private RandomService randomService = RandomServiceImpl.getInstance();

    private PlayerInputOutputServiceImpl() {
    }

    @Override
    public List<String> writePlayersToFile(String filePath, int collectionSize, int ageFrom, int ageTo, int threadsNumber) {
        List<Future<String>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        for (int i = 0; i < threadsNumber; i++) {
            int namePostfix = randomService.getNumber(0, 100);
            String fileName = filePath + File.separator + namePostfix;
            File file = validateFile(fileName);
            Callable<String> callable = new PlayerGeneratorTask(file, collectionSize, ageFrom, ageTo);
            Future<String> submit = executorService.submit(callable);
            futureList.add(submit);
        }
        executorService.shutdown();
        return getFutureStringResults(futureList);
    }

    @Override
    public List<String> readPlayersNamesFromFile(List<String> fileNames, int ageFrom, int ageTo, boolean isActiveCondition) {
        List<Future<List<Player>>> futureList = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(fileNames.size());
        for (String fileName : fileNames) {
            File file = validateFile(fileName);
            Callable<List<Player>> callable = new PlayerReaderTask(file, ageFrom, ageTo, isActiveCondition);
            Future<List<Player>> submit = executorService.submit(callable);
            futureList.add(submit);
        }
        executorService.shutdown();
        List<String> names = getFuturePlayerNamesResult(futureList);
        return names;
    }

    private File validateFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return file;
    }

    private List<String> getFutureStringResults(List<Future<String>> futureList) {
        List<String> results = new ArrayList<>();
        for (Future<String> stringFuture : futureList) {
            try {
                String result = stringFuture.get();
                results.add(result);
            } catch (InterruptedException | ExecutionException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return results;
    }

    private List<String> getFuturePlayerNamesResult(List<Future<List<Player>>> futureList) {
        List<String> names = new ArrayList<>(futureList.size());
        try {
            for (Future<List<Player>> listFuture : futureList) {
                List<Player> players = listFuture.get();
                for (Player player : players) {
                    String name = player.getName();
                    names.add(name);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return names;
    }

    public static PlayerInputOutputService getInstance() {
        if (instance == null) {
            instance = new PlayerInputOutputServiceImpl();
        }
        return instance;
    }
}
