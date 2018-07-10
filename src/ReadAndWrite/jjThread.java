package ReadAndWrite;

public class jjThread extends Thread{
    private lwfService ls;

    public jjThread(lwfService lwf) {
        this.ls = lwf;
    }

    public void run() {
        ls.writeMethod();
    }
}
