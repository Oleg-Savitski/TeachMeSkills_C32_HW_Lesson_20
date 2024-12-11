package com.teachmeskills.lesson_20.task_1.actions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class NewsReader implements Runnable {
    private final String name;

    public NewsReader(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(getCurrentTime() + " " + name + " started reading the news.");
        try {
            Thread.sleep(new Random().nextInt(3000));
            System.out.println(getCurrentTime() + " " + name + " finished reading the news.");
        } catch (InterruptedException e) {
            System.out.println(getCurrentTime() + " reading the news is interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }
}