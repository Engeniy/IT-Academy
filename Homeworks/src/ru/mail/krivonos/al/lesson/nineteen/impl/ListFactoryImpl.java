package ru.mail.krivonos.al.lesson.nineteen.impl;

import ru.mail.krivonos.al.lesson.nineteen.ListFactory;
import ru.mail.krivonos.al.lesson.nineteen.RandomService;
import ru.mail.krivonos.al.lesson.nineteen.model.ListType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListFactoryImpl implements ListFactory {

    private static ListFactory instance;
    private RandomService randomService = RandomServiceImpl.getInstance();

    private ListFactoryImpl() {
    }

    @Override
    public List<Integer> createFilledList(ListType type, int size, int rangeStart, int rangeEnd) {
        List<Integer> list;
        switch (type) {
            case ARRAY_LIST:
                list = new ArrayList<>(size);
                break;
            case LINKED_LIST:
                list = new LinkedList<>();
                break;
            default:
                throw new UnsupportedOperationException("Unsupported enum type!");
        }
        randomService.fillList(list, size, rangeStart, rangeEnd);
        return list;
    }

    public static ListFactory getInstance() {
        if (instance == null) {
            instance = new ListFactoryImpl();
        }
        return instance;
    }
}
