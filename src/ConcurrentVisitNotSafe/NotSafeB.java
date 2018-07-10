package ConcurrentVisitNotSafe;

public class NotSafeB extends Thread {
    private Data data;

    public NotSafeB(Data d) {
        super();
        this.data = d;
    }

    @Override
    public void run() {
        data.setNum("b");
    }
}
