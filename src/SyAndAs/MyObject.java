package SyAndAs;

public class MyObject {
    synchronized public void methodA() {
        try {
            System.out.println("methodA begin threadName= "+Thread.currentThread().getName());
            Thread.sleep(3000);
            System.out.println("methodA end time= "+System.currentTimeMillis());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodB() {
        System.out.println("methodB begin threadName= "+Thread.currentThread().getName());
        System.out.println("methodB end");
    }
}
