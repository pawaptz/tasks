package com.ru.msk.pp;

public class PingPongWaitNotify implements PingPongParallel {

    private boolean pingToPong = true;

    @Override
    public void ping() {
        synchronized (this) {
            while (!pingToPong) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            System.out.println("ping");
            pingToPong = false;
            notify();
        }
    }

    @Override
    public void pong() {
        synchronized (this) {
            while (pingToPong) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            System.out.println("pong");
            pingToPong = true;
            notify();
        }
    }
}
