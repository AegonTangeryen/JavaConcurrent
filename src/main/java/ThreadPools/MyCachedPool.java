package ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// CachedThreadPool来一个任务新建一个线程，线程空闲60s销毁
public class MyCachedPool {
    public static void main(String zining[]) {
        ExecutorService service = Executors.newCachedThreadPool();
        System.out.println(service);

        for (int i=0;i<2;i++) {
            service.execute( () -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });
        }

        System.out.println(service);
        try {
            TimeUnit.SECONDS.sleep(80);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(service);

        service.shutdown();
    }
}
