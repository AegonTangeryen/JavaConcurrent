package StartAndStop;

/*
 *
 *
 */

public class Main {
    public static void main(String[] args) {
        try {
            ByThread bt1 = new ByThread(1);
            bt1.setPriority(Thread.NORM_PRIORITY);
            bt1.start();
            Thread.sleep(200);
            bt1.interrupt();
        }catch (InterruptedException e) {
            System.out.println("这是main方法的catch");
            e.printStackTrace();
        }
    }
}
