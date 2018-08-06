package ThreadPools;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoin {
    static int nums[] = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for (int i=0;i<nums.length;i++) {
            nums[i] = r.nextInt(100);
        }
        System.out.println(Arrays.stream(nums).sum()); // stream API jdk1.8新特性
    }

    static class AddTask extends RecursiveTask<Long> {
        int start;
        int end;

        AddTask(int s,int e) {
            this.start = s;
            this.end = e;
        }

        @Override
        protected Long compute() {
            if (end-start <= MAX_NUM) { // 如果切分的区间少于50000，则单线程
                long sum = 0L;
                for (int i=start;i<end;i++) {
                    sum+=nums[i];
                }
                System.out.println("from:"+start+"to:"+end+" = "+sum);

                return sum;
            } else { // 如果大于50000，则分成小任务
                int middle = start+(end-start)/2;
                AddTask subTask1 = new AddTask(start,middle);
                AddTask subTask2 = new AddTask(middle,end);
                subTask1.fork();
                subTask2.fork();

                return subTask1.join()+subTask2.join();
            }
        }
    }

    // 高颖浠-你的甜蜜
    public static void main(String gaoyingxi[]) {

        ForkJoinPool fjp = new ForkJoinPool();
        AddTask task = new AddTask(0,nums.length);
        fjp.execute(task);
        long result = task.join();
        System.out.println(result);

    }
}
