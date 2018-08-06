package ConcurrentContainers;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TicketSeller {
    // 并发容器
    static Queue<String> tickets = new ConcurrentLinkedQueue<>();

    // 静态代码块，在类加载时运行一次
    static {
        for (int i=0;i<1000;i++)
            tickets.add("票号："+i);
    }

    public static void main(String[] fujing) {
        for (int i=0;i<10;i++) {
            new Thread(() -> {
                while (true) {
                    String s = tickets.poll();
                    if (s==null) break;
                    else System.out.println("销售了 "+s);
                }
            }).start();
        }
    }
}
