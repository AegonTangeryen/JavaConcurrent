package ReadAndRead;

public class MyThread extends Thread {
    private lwfService ls;

    public MyThread(lwfService lwf) {
        this.ls = lwf;
    }

    public void run() {
        ls.read(); // 调用加锁的方法
    }
}