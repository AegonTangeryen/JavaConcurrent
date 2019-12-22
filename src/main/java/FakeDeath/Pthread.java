package FakeDeath;

public class Pthread extends Thread {
    private P p;

    public Pthread(P p) {
        this.p = p;
    }

    public void run() {
        while (true) {
            p.setValue();
        }
    }
}
