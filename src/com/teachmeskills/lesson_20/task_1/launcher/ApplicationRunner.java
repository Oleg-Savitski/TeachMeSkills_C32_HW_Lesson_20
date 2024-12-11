package com.teachmeskills.lesson_20.task_1.launcher;

import com.teachmeskills.lesson_20.task_1.actions.Breakfast;
import com.teachmeskills.lesson_20.task_1.actions.Coffee;
import com.teachmeskills.lesson_20.task_1.actions.NewsReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/** Create a program that simulates the morning: reading news, tomorrow, coffee.
 * Set a name and priority for each thread from the config file.
 * Let there be 3 streams.
 * Create and run 3 threads.
 * Make two options: using the Runnable interface and/or using the Thread class.
 * I used the interface.
 */

public class ApplicationRunner {
    public static void main(String[] args) {

        Properties properties = new Properties();
        CountDownLatch coffeeReadyLatch = new CountDownLatch(1);

        try {
            properties.load(new FileInputStream("C:\\Java-job\\TeachMeSkills_C32_HW_Lesson_20\\resources\\config.properties"));

            String newsThreadName = properties.getProperty("news.thread.name");
            String breakfastThreadName = properties.getProperty("breakfast.thread.name");
            String coffeeThreadName = properties.getProperty("coffee.thread.name");

            Thread newsThread = new Thread(new NewsReader(newsThreadName));
            Thread coffeeThread = new Thread(new Coffee(coffeeThreadName, coffeeReadyLatch));
            Thread breakfastThread = new Thread(new Breakfast(breakfastThreadName, coffeeReadyLatch));

            newsThread.setPriority(Integer.parseInt(properties.getProperty("news.thread.priority")));
            breakfastThread.setPriority(Integer.parseInt(properties.getProperty("breakfast.thread.priority")));
            coffeeThread.setPriority(Integer.parseInt(properties.getProperty("coffee.thread.priority")));

            newsThread.start();
            coffeeThread.start();
            breakfastThread.start();

            newsThread.join();
            coffeeThread.join();
            breakfastThread.join();

        } catch (IOException e) {
            System.out.println("Configuration loading error: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("The main stream has been interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        } catch (NumberFormatException e) {
            System.out.println("Error converting the priority of the stream: " + e.getMessage());
        }
    }
}