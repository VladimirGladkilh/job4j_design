package barierthried;

import net.jcip.annotations.GuardedBy;

public class CountBarrier {
    @GuardedBy("this")
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            System.out.println(String.format("%s count", Thread.currentThread().getName()));
            notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (monitor) {
            if (count == this.total) {
                System.out.println(String.format("%s notify", Thread.currentThread().getName()));
                this.monitor.notify();
            } else {
                while (count < total) {
                    System.out.println(String.format("%s wait", Thread.currentThread().getName()));
                    this.monitor.wait();
                }

            }
        }
    }

    public static void main(String[] args) {
        int count = 20;
        CountBarrier countBarrier = new CountBarrier(count);

        for (int i=0; i < count; i++) {
            Thread thread = getThread(countBarrier);
            thread.start();
        }
    }

    private static Thread getThread(CountBarrier countBarrier) {

        Thread thread = new Thread(() -> {
            countBarrier.count();
            try {
                countBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s finish", Thread.currentThread().getName()));
        });
        return thread;
    }
}