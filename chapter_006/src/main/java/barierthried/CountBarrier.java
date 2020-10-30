package barierthried;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        count++;
    }

    public void await() throws InterruptedException {
        if (count == this.total) {
            this.monitor.notify();
        } else {
            this.monitor.wait();
        }
    }
}