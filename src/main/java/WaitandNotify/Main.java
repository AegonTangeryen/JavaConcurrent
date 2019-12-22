package WaitandNotify;

public class Main {
    public static void main(String[] args) {
        try {
            // wait 和 notify要把同一个对象当锁
            Object lock = new Object();
            ForWait fl = new ForWait(lock);
            fl.start();
            Thread.sleep(3000);
            ForNotify fn = new ForNotify(lock);
            fn.start();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
