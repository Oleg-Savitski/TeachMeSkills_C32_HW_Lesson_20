package com.teachmeskills.lesson_20.task_2.model;
import com.teachmeskills.lesson_20.task_2.utils.Constants;

import java.util.LinkedList;
import java.util.Queue;

public class ServiceStation implements Constants {

    private final Queue<String> cars = new LinkedList<>();

    public synchronized void addCar(String car) throws InterruptedException {
        while (cars.size() >= MAX_CARS_CONCURRENT) {
            System.out.println("The service station is full. Waiting for the car to free up space -> : " + car);
            wait();
        }
        cars.add(car);
        System.out.println("The car was added to the service station -> " + car);
        notifyAll();
    }

    public synchronized void removeCar() throws InterruptedException {
        while (cars.isEmpty()) {
            System.out.println("There are no pick-up machines. Waiting for the car to appear.");
            wait();
        }
        String car = cars.poll();
        System.out.println("The car was taken from the service station -> " + car);
        notifyAll();
    }
}