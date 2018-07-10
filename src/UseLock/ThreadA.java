package UseLock;

public class ThreadA extends Thread {
    private lwfService ls;

    public ThreadA(lwfService lwf) {
        this.ls = lwf;
    }

    public void run() {
        ls.methodA(); // 调用加锁的方法
    }
}
