package ThreadPools;

import java.util.ArrayList;
import java.util.concurrent.*;

// 求1到200000之间有多少质数，单线程和多线程并行计算对比
public class ParallelComputing {
    public static void main(String liurenyu[]) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        ArrayList<Integer> list = getPrime(1,200000);
        long end = System.currentTimeMillis();
        System.out.println("单线程用时： "+(end-start)+"ms");

        final int cpuCores = 4;
        ExecutorService service = Executors.newFixedThreadPool(5);
        // 将任务分成4片,这里没有平均分，因为后面的数比较大，算的慢
        MyTask t1 = new MyTask(1,80000);
        MyTask t2 = new MyTask(80001,130000);
        MyTask t3 = new MyTask(130001,180000);
        MyTask t4 = new MyTask(180001,200000);

        Future<ArrayList<Integer>> f1 = service.submit(t1);
        Future<ArrayList<Integer>> f2 = service.submit(t2);
        Future<ArrayList<Integer>> f3 = service.submit(t3);
        Future<ArrayList<Integer>> f4 = service.submit(t4);

        start = System.currentTimeMillis();
        f1.get();
        f2.get();
        f3.get();
        f4.get();
        end = System.currentTimeMillis();
        System.out.println("多线程并行用时： "+(end-start)+"ms");

        service.shutdown();
    }

    static class MyTask implements Callable<ArrayList<Integer>> {
        int startPos,endPos;

        MyTask(int s,int e) {
            this.startPos = s;
            this.endPos = e;
        }

        public ArrayList<Integer> call() {
            ArrayList<Integer> hal = getPrime(startPos,endPos);
            return hal;
        }
    }


    // 判断一个整数是不是质数
    public static boolean isPrime(int num) {
        for (int i=2;i<=num/2;i++) {
            if (num%i==0) return false;
        }
        return true;
    }

    // 给出一个整数范围，判断这个范围中的整数有多少个质数
    public static ArrayList<Integer> getPrime(int start,int end) {
        ArrayList<Integer> results = new ArrayList<>();
        for (int i=start;i<=end;i++) {
            if (isPrime(i)) results.add(i);
        }
        return results;
    }
}
