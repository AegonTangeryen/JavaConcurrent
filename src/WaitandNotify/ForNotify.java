package WaitandNotify;

public class ForNotify extends Thread {
    private Object lock;

    public ForNotify(Object lo) {
        this.lock = lo;
    }

    public void run() {
        synchronized (lock) {
            System.out.println("begin notifying time is"+System.currentTimeMillis());
            lock.notify();
            System.out.println("stop notifying time is"+System.currentTimeMillis());
        }
    }
}
