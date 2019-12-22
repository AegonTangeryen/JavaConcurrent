package ReadAndWrite;

public class Produce101 {
    public static void main(String[] args) {
        lwfService lwf = new lwfService();

        jjThread jj = new jjThread(lwf);
        jj.setName("a");
        jj.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        lwfThread lt = new lwfThread(lwf);
        lt.setName("b");
        lt.start();
    }
}
