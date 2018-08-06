package ThreadPools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 自定义线程池
public class CustomThreadPool extends ThreadPoolExecutor {
    public CustomThreadPool(int corePoolSize,  //最小线程数
                            int maximumPoolSize, //最大线程数
                            long keepAliveTime, //存活时间
                            TimeUnit unit, //时间单位
                            BlockingQueue<Runnable> workQueue) { //任务存放的并发容器
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
}
