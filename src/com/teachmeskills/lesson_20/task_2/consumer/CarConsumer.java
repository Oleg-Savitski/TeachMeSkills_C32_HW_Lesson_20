package com.teachmeskills.lesson_20.task_2.consumer;

import com.teachmeskills.lesson_20.task_2.model.ServiceStation;

public class CarConsumer implements Runnable {
    private final ServiceStation serviceStation;

    public CarConsumer(ServiceStation serviceStation) {
        this.serviceStation = serviceStation;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 10; i++) {
                serviceStation.removeCar();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}