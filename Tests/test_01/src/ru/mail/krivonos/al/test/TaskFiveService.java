package ru.mail.krivonos.al.test;

import ru.mail.krivonos.al.test.model.Car;

import java.util.List;
import java.util.Map;

public interface TaskFiveService {

    List<Car> getCarList(int quantity, int fromCapacity, int toCapacity);

    Map<Integer, List<Car>> groupByEngineCapacity(List<Car> cars);

    void printCarGroup(Map<Integer, List<Car>> map, int chosenCapacity);
}
