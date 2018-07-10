package PipedCom;

import java.io.PipedOutputStream;

public class WriteThread extends Thread {
    private WriteData write;
    private PipedOutputStream out;

    public WriteThread(WriteData write,PipedOutputStream out) {
        this.write = write;
        this.out = out;
    }

    public void run() {
        write.writeMethod(out);
    }
}
