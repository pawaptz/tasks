package com.ru.msk.pp;

import java.util.concurrent.atomic.AtomicBoolean;

public class PingPongBusyWait implements PingPongParallel {

    private final AtomicBoolean pingToPong = new AtomicBoolean(false);

    @Override
    public void ping() {
        while (!pingToPong.compareAndSet(false, true)){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        System.out.println("ping");
    }

    @Override
    public void pong() {
        while (!pingToPong.compareAndSet(true, false)){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        System.out.println("pong");
    }
}
