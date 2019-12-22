package UseLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class UseTryLock {
    private Lock lock = new ReentrantLock(); // 锁对象
    public void method() {
        if (lock.tryLock()) { // 锁定
            try {
                System.out.println("method begin Thread name= "+Thread.currentThread().getName()+"  time is  "+System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("method end Thread name= "+Thread.currentThread().getName()+"  time is  "+System.currentTimeMillis());
            }catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock(); // 释放锁
            }
        }
    }
}
