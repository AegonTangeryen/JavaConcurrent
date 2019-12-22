package ConcurrentContainers;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// 写时复制。读的效率非常高，写的效率非常低。
// 适合读多写少的应用场景,读的时候不用加锁
// 写的时候复制容器,大量的写会消耗内存
public class CopyOnWrite {
//    static List<String> list = new ArrayList<>(); // 172ms,99872个，线程不安全；读：20757ms
//    static List<String> list = new Vector<>(); // 109ms,100000个,线程安全;读：6333ms
    static List<String> list = new CopyOnWriteArrayList<>(); // 写；5132ms,100000个;读：1700ms

    static {
        for (int i=0;i<100;i++) {
            list.add("lwf");
        }
    }

    public static void main(String xumengjia[]) {
        Random r = new Random();
        Thread[] ths = new Thread[100];

        for(int i=0;i<ths.length;i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int i=0;i<1000;i++) {
                        list.forEach(s -> s.length());
                    }
                }
            };
            ths[i] = new Thread(task);
        }
        runAndCompleteTime(ths);
        System.out.println("size= "+list.size());
    }

    public static void runAndCompleteTime(Thread[] ths) {
        long start = System.currentTimeMillis();
        Arrays.asList(ths).forEach(thread -> thread.start());
        Arrays.asList(ths).forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println("time= "+(end-start));
    }
}
