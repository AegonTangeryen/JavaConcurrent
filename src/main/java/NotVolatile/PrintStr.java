package NotVolatile;

public class PrintStr extends Thread {
    private volatile boolean isContinue = true;

    public boolean isContinue() {
        return isContinue;
    }

    public void setContinue(boolean aContinue) {
        isContinue = aContinue;
    }

    public void run() {
        System.out.println("enter run");
        try {
            while (isContinue) {
                System.out.println("run printMethod "+Thread.currentThread().getName());
                Thread.sleep(1000);
            }
            System.out.println("leave run");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
