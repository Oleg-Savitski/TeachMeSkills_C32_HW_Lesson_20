package com.teachmeskills.lesson_20.task_2.launcher;

import com.teachmeskills.lesson_20.task_2.consumer.CarConsumer;
import com.teachmeskills.lesson_20.task_2.model.ServiceStation;
import com.teachmeskills.lesson_20.task_2.producer.CarProducer;

/** There are a HUNDRED. A maximum of a certain number of machines can be
 * serviced at a service station.
 * Create a class that will run in a separate thread and will add machines
 * to the service station for discussion.
 * Create a class that will run in a separate thread and will pick up
 * fixed machines from the service station.
 * Let the maximum number of machines available for discussion be set
 * in the interface for storing constants.
 * Use synchronized, wait, notify.
 */

public class ApplicationRunner {
    public static void main(String[] args) {

        ServiceStation serviceStation = new ServiceStation();

        Thread producerThread = new Thread(new CarProducer(serviceStation));
        Thread consumerThread = new Thread(new CarConsumer(serviceStation));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}