package ru.mail.krivonos.al.test.impl;

import ru.mail.krivonos.al.test.TaskFiveService;
import ru.mail.krivonos.al.test.model.Car;

import java.util.*;

public class TaskFiveServiceImpl implements TaskFiveService {

    private Random random;

    public TaskFiveServiceImpl() {
        random = new Random();
    }

    @Override
    public List<Car> getCarList(int quantity, int fromCapacity, int toCapacity) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < quantity; i++) {
            cars.add(new Car("Name" + i, "Model" + i, random.nextInt(toCapacity - fromCapacity + 1) + fromCapacity));
        }
        return cars;
    }

    @Override
    public Map<Integer, List<Car>> groupByEngineCapacity(List<Car> cars) {
        Map<Integer, List<Car>> map = new HashMap<>();
        for (Car car : cars) {
            if (map.containsKey(car.getEngineCapacity())) {
                map.get(car.getEngineCapacity()).add(car);
            } else {
                map.put(car.getEngineCapacity(), new ArrayList<>());
                map.get(car.getEngineCapacity()).add(car);
            }
        }
        return map;
    }

    @Override
    public void printCarGroup(Map<Integer, List<Car>> map, int chosenCapacity) {
        for (Car car : map.get(chosenCapacity)) {
            System.out.println(car);
        }
    }
}
