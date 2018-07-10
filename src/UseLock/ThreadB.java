package UseLock;

public class ThreadB extends Thread {
    private lwfService ls;

    public ThreadB(lwfService lwf) {
        this.ls = lwf;
    }

    public void run() {
        ls.methodA();
    }
}
