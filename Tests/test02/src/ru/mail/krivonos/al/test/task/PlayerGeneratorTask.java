package ru.mail.krivonos.al.test.task;

import ru.mail.krivonos.al.test.RandomService;
import ru.mail.krivonos.al.test.impl.RandomServiceImpl;
import ru.mail.krivonos.al.test.model.Player;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class PlayerGeneratorTask implements Callable<String> {

    private File file;
    private int collectionSize;
    private int ageFrom;
    private int ageTo;
    private RandomService randomService = RandomServiceImpl.getInstance();

    public PlayerGeneratorTask(File file, int collectionSize, int ageFrom, int ageTo) {
        this.file = file;
        this.collectionSize = collectionSize;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
    }

    @Override
    public String call() throws Exception {
        List<Player> players = randomService.getPlayersList(collectionSize, ageFrom, ageTo);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Player player : players) {
                bw.write(player.getName());
                bw.write(" ");
                int age = player.getAge();
                bw.write(String.valueOf(age));
                bw.write(" ");
                boolean isActive = player.isActive();
                bw.write(String.valueOf(isActive));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return file.getPath();
    }
}
