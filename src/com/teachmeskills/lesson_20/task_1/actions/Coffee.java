package com.teachmeskills.lesson_20.task_1.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class Coffee implements Runnable {
    private final String name;
    private final CountDownLatch coffeeReadyLatch;

    public Coffee(String name, CountDownLatch coffeeReadyLatch) {
        this.name = name;
        this.coffeeReadyLatch = coffeeReadyLatch;
    }

    @Override
    public void run() {
        System.out.println(getCurrentTime() + " " + name + " started making coffee.");
        try {
            Thread.sleep(new Random().nextInt(3000));
            System.out.println(getCurrentTime() + " " + name + " finished making coffee.");
        } catch (InterruptedException e) {
            System.out.println(getCurrentTime() + " coffee preparation has been interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            coffeeReadyLatch.countDown();
        }
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}