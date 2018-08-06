package ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// 线程池中只有一个线程，保证任务完成的先后顺序
public class SinglePool {
    public static void main(String zining[]) {
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i=0;i<5;i++) {
            final int j=i;
            service.execute( () -> {
                System.out.println(j+" "+Thread.currentThread().getName());
            });
        }
        service.shutdown();
    }
}
