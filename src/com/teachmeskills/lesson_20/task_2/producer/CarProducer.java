package com.teachmeskills.lesson_20.task_2.producer;


import com.teachmeskills.lesson_20.task_2.model.ServiceStation;

public class CarProducer implements Runnable {
    private final ServiceStation serviceStation;

    public CarProducer(ServiceStation serviceStation) {
        this.serviceStation = serviceStation;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                String car = "Automobile " + i;
                serviceStation.addCar(car);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}