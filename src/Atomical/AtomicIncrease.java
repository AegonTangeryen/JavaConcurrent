package Atomical;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIncrease extends Thread {
    private AtomicInteger count = new AtomicInteger(0);

    public void run() {
        for (int i=0;i<1000;i++) {
            System.out.println(count.incrementAndGet());
        }
    }
}
