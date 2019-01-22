package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.TaskFourService;
import ru.mail.krivonos.al.test.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskFourServiceImpl implements TaskFourService {

    private Random random;

    public TaskFourServiceImpl() {
        random = new Random();
    }

    // TODO add validation
    @Override
    public List<Player> generatePlayers(int quantity, int ageFrom, int ageTo) {
        List<Player> players = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            players.add(new Player("Name" + i, random.nextInt(ageTo - ageFrom + 1) + ageFrom, random.nextBoolean()));
        }
        return players;
    }

    @Override
    public void printActivePlayersWithTargetAge(List<Player> players, int ageFrom, int ageTo) {
        for (Player player : players) {
            if (player.getAge() >= ageFrom && player.getAge() <= ageTo && player.isActive()) {
                System.out.println(player);
            }
        }
    }
}
