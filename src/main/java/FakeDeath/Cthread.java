package FakeDeath;

public class Cthread extends Thread {
    private C c;

    public Cthread(C c) {
        this.c = c;
    }

    public void run() {
        while (true) {
            c.getValue();
        }
    }
}
