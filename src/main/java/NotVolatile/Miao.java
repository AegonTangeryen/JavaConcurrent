package NotVolatile;

public class Miao {
    public static void main(String[] args) {
        PrintStr ps = new PrintStr();
        ps.start();
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("我是主线程，我要停止子线程");
        ps.setContinue(false);
    }
}
