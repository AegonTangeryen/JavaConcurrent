package SyAndAs;

public class ThreadA extends Thread {
    private MyObject object;

    public ThreadA(MyObject obj) {
        this.object = obj;
    }

    @Override
    public void run() {
        object.methodA();
    }
}
