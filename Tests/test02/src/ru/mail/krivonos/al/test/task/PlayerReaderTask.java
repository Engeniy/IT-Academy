package ru.mail.krivonos.al.test.task;

import ru.mail.krivonos.al.test.model.Player;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PlayerReaderTask implements Callable<List<Player>> {

    private File file;
    private int ageFrom;
    private int ageTo;
    private boolean isActiveCondition;

    public PlayerReaderTask(File file, int ageFrom, int ageTo, boolean isActiveCondition) {
        this.file = file;
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.isActiveCondition = isActiveCondition;
    }

    @Override
    public List<Player> call() throws Exception {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(" ");
                String playerName = fields[0];
                int playerAge = Integer.valueOf(fields[1]);
                boolean isActive = Boolean.valueOf(fields[2]);
                if (isActive == isActiveCondition && playerAge >= ageFrom && playerAge <= ageTo) {
                    Player player = new Player(playerName, playerAge, isActive);
                    players.add(player);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return players;
    }
}
