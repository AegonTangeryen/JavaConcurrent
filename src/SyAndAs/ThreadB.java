package SyAndAs;

public class ThreadB extends Thread {
    private MyObject object;

    public ThreadB(MyObject obj) {
        this.object = obj;
    }

    @Override
    public void run() {
        object.methodB();
    }
}
