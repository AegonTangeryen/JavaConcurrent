package Atomical;

public class Run {
    public static void main(String[] args) {
        AtomicIncrease ai = new AtomicIncrease();
        Thread t1 = new Thread(ai);
        t1.start();
        Thread t2 = new Thread(ai);
        t2.start();
        Thread t3 = new Thread(ai);
        t3.start();
        Thread t4 = new Thread(ai);
        t4.start();
        Thread t5 = new Thread(ai);
        t5.start();
    }
}
