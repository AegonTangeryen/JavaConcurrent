package ReadAndRead;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class lwfService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        lock.readLock().lock(); // 竞争读取锁
        try {
            System.out.println("获得读锁 "+Thread.currentThread().getName()+"  "+System.currentTimeMillis());
            Thread.sleep(10000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放读锁 "+Thread.currentThread().getName()+"  "+System.currentTimeMillis());
            lock.readLock().unlock(); // 释放读取锁
        }
    }
}
