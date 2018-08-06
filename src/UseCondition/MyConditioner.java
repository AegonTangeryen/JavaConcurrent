package UseCondition;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 生产者-消费者模式
public class MyConditioner <T> {
    final private LinkedList<T> list = new LinkedList<T>(); // 公共容器
    final private int MAX = 10; // 最大容量
    private int count = 0; // 当前容量

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition(); // 唤醒生产者
    private Condition consumer = lock.newCondition(); // 唤醒消费者

    // 生产者向容器中添加元素
    public void put(T t) {
        lock.lock();
        try {
            while (list.size()==MAX) { // 如果容器已满，则生产者等待。此处必须用while而不能用if
                producer.await();
            }
            // 如果容器没有满，则生产者往容器中添加一个元素
            list.add(t);
            ++count;
            consumer.signalAll(); // 然后唤醒所有消费者
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock(); // 解锁必须放在finally中
        }
    }

    // 消费者取商品
    public T get() {
        T t = null;
        lock.lock();
        try {
            while (list.size()==0) {
                consumer.await(); // 如果容器为空，即没有商品，则消费者等待
            }
            // 如果容器不为空，则取走一个
            t = list.removeFirst();
            count--;
            producer.signalAll(); // 唤醒所有生产者
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }

    public static void main(String[] zhangzining) {
        MyConditioner<String> c = new MyConditioner<String>();
        // 启动消费者线程
        for (int i=0;i<10;i++) {
            new Thread(()->{
                for (int j=0;j<5;j++) System.out.println(c.get());
            },"c"+i).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 启动生产者线程
        for (int i=0;i<2;i++) {
            new Thread(() -> {
                for (int j=0;j<25;j++) c.put(Thread.currentThread().getName()+" "+j);
            }, "p"+i).start();
        }
    }
}
