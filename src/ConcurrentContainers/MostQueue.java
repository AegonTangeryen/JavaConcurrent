package ConcurrentContainers;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.*;

// 在并发容器中应用的最多
public class MostQueue {
// linkedblockingqueue 无界队列，容量无限，内存为上限
// ArrayBlockingQueue  有界队列，容量有限。能装的元素格式有限的。
// DelayQueue 定时任务，等待时间最长的任务最先执行
    static BlockingQueue<String> strs = new LinkedBlockingQueue<>(); // 生产者-消费者模型的实现
//        Queue<String> strs = new ConcurrentLinkedQueue<>();// CAS无锁实现
    static Random r = new Random();

    public static void main(String liziting[]) {
        new Thread( () -> {
            while (true) {
                for (int i=0;i<10;i++) {
                    try {
                        strs.put("a"+i); // 阻塞式容器的方法，如果满了，put方法等待,await
                        TimeUnit.MILLISECONDS.sleep(r.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"p1").start();

        for (int i=0;i<5;i++) {
            new Thread( () -> {
                for (;;) {
                    try {
                        // 与put相对的方法，如果队列空了，take就阻塞等待
                        System.out.println(Thread.currentThread().getName()+" take- "+strs.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"c"+i).start();
        }
    }
}


/*
因此Java5.0开始针对多线程并发访问设计，提供了并发性能较好的并发容器，引入了java.util.concurrent包。
为了达到尽可能提高效率的目标，Java源码中采用了多种优化方式来提高并发容器的执行效率或者使用方便性，核心的就是：锁，CAS（无锁），COW（读写分离），分段锁。
基于 锁 来实现
生产者-消费者模型的实现，LinkedBlockingQueue
其实从这里大家应该明白了阻塞队列的实现原理，事实它和我们用Object.wait()、Object.notify()和非阻塞队列实现生产者-消费者的思路类似，
只不过它把这些工作一起集成到了阻塞队列中实现。
可以看到, 使用阻塞队列代码要简单得多，不需要再单独考虑同步和线程间通信的问题。
在并发编程中，一般推荐使用阻塞队列，这样实现可以尽量地避免程序出现意外的错误。
*
* 基于 CAS操作 来实现
* 非阻塞链表 ConcurrentLinkedQueue
* CAS是一种无锁的非阻塞算法，全称为：Compare-and-swap（比较并交换），大致思路是：
* 先比较目标对象现值是否和旧值一致，如果一致，则更新对象为新值；如果不一致，则表明对象已经被其他线程修改，直接返回。
*
*基于 COW操作 来实现
*  Java中，List在遍历的时候，如果被修改了会抛出java.util.ConcurrentModificationException错误。所以衍生出一种要求, 读的时候可以改, 这样就不会造成读的效率太低。
    Copy-On-Write简称COW，是一种用于程序设计中的优化策略。其基本思路是，从一开始大家都在共享同一个内容，当某个人想要修改这个内容的时候，才会真正把内容Copy出去形成一个新的内容然后再改，这是一种延时懒惰策略。
    通俗的理解是当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器。
    这样做的好处是我们可以对CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。所以CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。

    基于 分段锁 来实现
    ConcurrentHashMap允许多个修改操作并发进行，其关键在于使用了锁分离技术。它使用了多个锁来控制对hash表的不同部分进行的修改。ConcurrentHashMap内部使用段(Segment)来表示这些不同的部分，每个段其实就是一个小的hash table，它们有自己的锁。只要多个修改操作发生在不同的段上，它们就可以并发进行。

     有些方法需要跨段，比如size()和containsValue()，它们可能需要锁定整个表而而不仅仅是某个段，这需要按顺序锁定所有段，操作完毕后，又按顺序释放所有段的锁。
     这里“按顺序”是很重要的，否则极有可能出现死锁，在ConcurrentHashMap内部，段数组是final的，并且其成员变量实际上也是final的，但是，仅仅是将数组声明为final的并不保证数组成员也是final的，这需要实现上的保证。这可以确保不会出现死锁，因为获得锁的顺序是固定的。
* */