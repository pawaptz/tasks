package com.ru.msk.pp;

public class PingPongBusyWait implements PingPongParallel {

    private volatile boolean ping = true;

    @Override
    public void ping() {
        while (!Thread.currentThread().isInterrupted()) {
            while (!ping) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            synchronized (this){
                if (ping) {
                    System.out.println("ping");
                    ping = false;
                }
            }
        }
    }

    @Override
    public void pong() {
        while (!Thread.currentThread().isInterrupted()) {
            if (ping) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            synchronized (this) {
                if (!ping) {
                    System.out.println("pong");
                    ping = true;
                }
            }
        }
    }
}
