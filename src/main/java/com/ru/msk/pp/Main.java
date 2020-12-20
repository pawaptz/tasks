package com.ru.msk.pp;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final ExecutorService executor = Executors.newFixedThreadPool(20);
        //final PingPongParallel pp = new PingPongBusyWait();
        //final PingPongParallel pp = new PingPongWaitNotify();
        final PingPongParallel pp = new PingPongQueue();
        final Random random = new Random(100);
        for (int i = 0; i < 100; i++) {
            if (random.nextBoolean()) {
                executor.execute(pp::ping);
            } else {
                executor.execute(pp::pong);
            }
        }
        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }
}
