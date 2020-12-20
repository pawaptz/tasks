package com.ru.msk.pp;

import java.util.concurrent.SynchronousQueue;

public class PingPongQueue implements PingPongParallel {

    private final SynchronousQueue<Pp> pingPongQueue = new SynchronousQueue<>();

    public PingPongQueue() {
        Thread initThread = new Thread(this::init);
        initThread.start();
    }

    private void init() {
        try {
            pingPongQueue.put(Pp.PING);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void ping() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                final Pp p = pingPongQueue.take();
                if (p == Pp.PING) {
                    System.out.println("ping");
                    pingPongQueue.put(Pp.PONG);
                    break;
                } else {
                    pingPongQueue.put(p);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void pong() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                final Pp p = pingPongQueue.take();
                if (p == Pp.PONG) {
                    System.out.println("pong");
                    pingPongQueue.put(Pp.PING);
                    break;
                } else {
                    pingPongQueue.put(p);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

    enum Pp {
        PING, PONG
    }
}
