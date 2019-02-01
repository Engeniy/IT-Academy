package ru.mail.krivonos.al.lesson.nineteen;

import ru.mail.krivonos.al.lesson.nineteen.model.ListType;

import java.util.List;

public interface ListFactory {

    List<Integer> createFilledList(ListType type, int size, int rangeStart, int rangeEnd);
}
