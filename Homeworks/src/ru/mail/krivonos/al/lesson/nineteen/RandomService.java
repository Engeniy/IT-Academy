package ru.mail.krivonos.al.lesson.nineteen;

import java.util.List;

public interface RandomService {

    void fillList(List<Integer> list, int quantity, int rangeStart, int rangeEnd);

    int getYear(int rangeStart, int rangeEnd);
}
