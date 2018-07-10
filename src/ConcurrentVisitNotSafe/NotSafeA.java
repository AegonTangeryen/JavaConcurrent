package ConcurrentVisitNotSafe;

public class NotSafeA extends Thread {
    private Data data;

    public NotSafeA(Data d) {
        super();
        this.data = d;
    }

    @Override
    public void run() {
        data.setNum("a");
    }
}
