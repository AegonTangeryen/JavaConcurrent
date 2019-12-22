package ReadAndWrite;

public class lwfThread extends Thread {
    private lwfService ls;

    public lwfThread(lwfService lwf) {
        this.ls = lwf;
    }

    public void run() {
        ls.readMethod();
    }
}
