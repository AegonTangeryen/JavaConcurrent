package ThreadPools;

import java.util.concurrent.*;

public class OurFuture {
    public static void main(String[] liurenyu) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>( () -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();
        System.out.println(task.get());

        /*-------------------------------------------------------------------------------------------*/

        ExecutorService service = Executors.newFixedThreadPool(5);
        Future<Integer> f = service.submit( () -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(f.isDone());
        System.out.println(f.get()); // get是阻塞方法

        // 注意这里开了一个线程池，只要没有shutdown线程池就会一直存在，程序不会结束
        TimeUnit.SECONDS.sleep(5);
        service.shutdown();
    }
}
