package ConcurrentContainers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CountDownLatch;

// 各个容器在多线程并发时的效率对比
// ConcurrentHashMap在高并发时比hashtable效率要高一点,ConcurrentHashMap默认把锁定的数据段分为16段，并发操作
// jdk1.8之后ConcurrentHashMap放弃了segment分段设计，采用node+cas+synchronized
public class MyConcurrentMap {
    public static void main(String xumengjia[]) {
//        Map<String,String> map = new ConcurrentHashMap<>();
//        Map<String,String> map = new ConcurrentSkipListMap<>(); // 高并发而且需要排序,跳表
        Map<String,String> map = new Hashtable<>(); // 线程安全，效率低，不能存null值和null键，现在已经快被弃用
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<String, String>());

        Random r = new Random(); // 注意这是java.util中的random类
        Thread[] ths = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ths.length); // 一个门栓
        long start = System.currentTimeMillis();
        for (int i=0;i<ths.length;i++) {
            ths[i] = new Thread(() -> {
                for (int j=0; j<10000; j++) {
                    map.put("a"+r.nextInt(100000),"a"+r.nextInt(100000));
                    latch.countDown();
                }
            });
        }

        Arrays.asList(ths).forEach(t -> t.start());
        // 主线程等待
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }
}
