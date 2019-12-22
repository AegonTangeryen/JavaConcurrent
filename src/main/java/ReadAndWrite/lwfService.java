package ReadAndWrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class lwfService {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void readMethod() {
        lock.readLock().lock(); // 竞争读取锁
        try {
            System.out.println("获得读锁 "+Thread.currentThread().getName()+"  "+System.currentTimeMillis());
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放读锁 "+Thread.currentThread().getName()+"  "+System.currentTimeMillis());
            lock.readLock().unlock(); // 释放读取锁
        }
    }

    public void writeMethod() {
        lock.writeLock().lock(); // 竞争写入锁
        try {
            System.out.println("获得写锁 "+Thread.currentThread().getName()+"  "+System.currentTimeMillis());
            Thread.sleep(5000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("释放写锁 "+Thread.currentThread().getName()+"  "+System.currentTimeMillis());
            lock.writeLock().unlock(); // 释放写入锁
        }
    }
}
