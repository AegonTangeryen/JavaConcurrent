package ConcurrentContainers;

import java.util.concurrent.LinkedTransferQueue;

// SynchronousQueue容量为0的队列，必须有消费者取走才能add
public class TransferQueues {
    public static void main(String liziting[]) throws InterruptedException {
        LinkedTransferQueue<String> strs = new LinkedTransferQueue<>();

        new Thread( () -> {
            try {
                System.out.println(strs.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        strs.transfer("lwf"); // 如果找不到消费者就等待
    }
}
