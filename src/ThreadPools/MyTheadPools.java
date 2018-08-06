package ThreadPools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

// 第一次接触线程池
public class MyTheadPools {
    public static void main(String lizixuan[]) throws InterruptedException {
        // 开启了一个容量为5的定量线程池，现在还没有启动，需要的时候再启动这些线程
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i=0; i<6; i++) { // 分配6个任务给线程池
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

        service.shutdown(); // 等待所有任务执行完再关闭
        System.out.println(service.isTerminated()); // 线程池中所有任务执行完了吗
        System.out.println(service.isShutdown()); // 线程池现在关闭了吗
        System.out.println(service);

        TimeUnit.SECONDS.sleep(5);
        System.out.println(service.isTerminated());
        System.out.println(service.isShutdown());
        System.out.println(service);
    }
}
