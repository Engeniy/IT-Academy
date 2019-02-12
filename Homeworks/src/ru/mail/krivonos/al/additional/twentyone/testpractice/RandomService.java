package ru.mail.krivonos.al.additional.twentyone.testpractice;

import java.util.List;

public interface RandomService {

    int[] getArray(int size, int range);

    List<Integer> getList(int size, int range);

    int getNumber(int range);
}
