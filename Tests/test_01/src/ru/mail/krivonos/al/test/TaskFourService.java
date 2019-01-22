package ru.mail.krivonos.al.test;

import ru.mail.krivonos.al.test.model.Player;

import java.util.List;

public interface TaskFourService {

    List<Player> generatePlayers(int quantity, int ageFrom, int ageTo);

    void printActivePlayersWithTargetAge(List<Player> players, int ageFrom, int ageTo);
}
