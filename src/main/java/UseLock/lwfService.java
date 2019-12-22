package UseLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class lwfService {
    private Lock lock = new ReentrantLock(); // 锁对象

    public void methodA() {
        lock.lock(); // 锁定
        try {
            System.out.println("methodA begin Thread name= "+Thread.currentThread().getName()+"  time is  "+System.currentTimeMillis());
            Thread.sleep(2000);
            System.out.println("methodA end Thread name= "+Thread.currentThread().getName()+"  time is  "+System.currentTimeMillis());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock(); // 释放锁
        }
    }
}
