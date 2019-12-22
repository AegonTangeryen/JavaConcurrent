package UseLock;

public class MiaoMiao {
    public static void main(String[] args) {
        lwfService lwf = new lwfService();
        ThreadA ta = new ThreadA(lwf);
        ta.setName("a");
        ta.start();
        ThreadB tb = new ThreadB(lwf);
        tb.setName("b");
        tb.start();
    }
}
