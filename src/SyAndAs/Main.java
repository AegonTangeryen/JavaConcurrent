package SyAndAs;

public class Main {
    public static void main(String[] args) {
        MyObject yourObj = new MyObject();

        ThreadA a = new ThreadA(yourObj);
        a.setName("A");
        ThreadB b = new ThreadB(yourObj);
        b.setName("B");
        a.start();
        b.start();
    }
}
