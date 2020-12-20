package com.ru.msk.pp;

public class PingPongWaitNotify implements PingPongParallel {

    private boolean ping = true;

    @Override
    public void ping() {
        synchronized (this) {
            while (!ping) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            System.out.println("ping");
            ping = false;
            notify();
        }
    }

    @Override
    public void pong() {
        synchronized (this) {
            while (ping) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException(e);
                }
            }
            System.out.println("pong");
            ping = true;
            notify();
        }
    }
}
