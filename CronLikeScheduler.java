package com.example.reminder;

import java.util.concurrent.*;

public class CronLikeScheduler {

    public static void main(String[] args) throws InterruptedException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

        // Run every 30 seconds (cron-like "every N seconds")
        scheduler.scheduleAtFixedRate(
                () -> System.out.println("Running at " + java.time.Instant.now()),
                0,
                30,
                TimeUnit.SECONDS
        );

        // Run once after 5 seconds
        scheduler.scheduleAtFixedRate(
                () -> System.out.println("One-time task"),
                0,
                1,
                TimeUnit.SECONDS
        );

        Thread.sleep(120_000);
        scheduler.shutdown();
    }
}