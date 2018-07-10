package WaitandNotify;

public class ForWait extends Thread {
    private Object lock;

    public ForWait(Object lo) {
        this.lock = lo;
    }

    public void run() {
        try {
            synchronized (lock) {
                System.out.println("begin waiting time is "+System.currentTimeMillis());
                lock.wait();
                System.out.println("stop waiting time is"+System.currentTimeMillis());
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
