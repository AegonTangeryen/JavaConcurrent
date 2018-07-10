package ReadAndRead;

public class Mua {
    public static void main(String[] args) {
        lwfService lwf = new lwfService();
        MyThread a = new MyThread(lwf);

        Thread t1 = new Thread(a);
        t1.setName("t1");
        Thread t2 = new Thread(a);
        t2.setName("t2");
        Thread t3 = new Thread(a);
        t3.setName("t3");
        Thread t4 = new Thread(a);
        t4.setName("t4");
        Thread t5 = new Thread(a);
        t5.setName("t5");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
