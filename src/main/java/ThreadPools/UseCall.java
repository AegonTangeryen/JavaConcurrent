package ThreadPools;

/**
 *线程池的顶级接口Executor，只有一个方法需要重写execute()
 * ExecutorService接口，继承了Executor
 *
 */

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class UseCall implements Callable<String> {
    @Override
    public String call() {
        int sum = 0;

        for (int i = 0; i <= 100000; i++) {
            sum += i;
        }
        return sum+"";
    }

    public static void main(String[] fujing) {
        UseCall uc = new UseCall();
        //1.执行 Callable 方式，需要 FutureTask 实现类的支持，用于接收运算结果。
        FutureTask<String> ft = new FutureTask<>(uc);
        new Thread(ft).start();

        //2.接收线程运算后的结果
        try {
            int summer = Integer.parseInt(ft.get());
            System.out.println(summer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Executor e = new Executor() {
            @Override
            public void execute(Runnable command) {
            }
        };
        ExecutorService es = new ExecutorService() {
            @Override
            public void shutdown() {

            }

            @Override
            public List<Runnable> shutdownNow() {
                return null;
            }

            @Override
            public boolean isShutdown() {
                return false;
            }

            @Override
            public boolean isTerminated() {
                return false;
            }

            @Override
            public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public <T> Future<T> submit(Callable<T> task) {
                return null;
            }

            @Override
            public <T> Future<T> submit(Runnable task, T result) {
                return null;
            }

            @Override
            public Future<?> submit(Runnable task) {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
                return null;
            }

            @Override
            public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks) throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public <T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }

            @Override
            public void execute(Runnable command) {

            }
        };
    }
}
