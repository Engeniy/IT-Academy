package ru.mail.krivonos.al.project_jd1.services;

import ru.mail.krivonos.al.project_jd1.repository.model.Item;

import java.util.List;

public interface RandomService {

    Item getItem(int rangeStart, int rangeEnd);

    List<Item> getItemList(int size, int rangeStart, int rangeEnd);
}
