package com.teachmeskills.lesson_20.task_1.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Breakfast implements Runnable {
    private final String name;
    private final CountDownLatch coffeeReadyLatch;

    public Breakfast(String name, CountDownLatch coffeeReadyLatch) {
        this.name = name;
        this.coffeeReadyLatch = coffeeReadyLatch;
    }

    @Override
    public void run() {
        try {
            coffeeReadyLatch.await();
            System.out.println(getCurrentTime() + " " + name + " started making breakfast.");
            Thread.sleep(new Random().nextInt(3000));
            System.out.println(getCurrentTime() + " " + name + " finished making breakfast.");
        } catch (InterruptedException e) {
            System.out.println(getCurrentTime() + " breakfast preparation is interrupted " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}