package FakeDeath;

public class miaomiaomiao {
    public static void main(String[] args) throws InterruptedException {
        String lock = new String("");
        P p = new P(lock);
        C c = new C(lock);

        // 2生产者2消费者
        Pthread[] pthreads = new Pthread[3];
        Cthread[] cthreads = new Cthread[3];

        for (int i=0; i<3; i++) {
            pthreads[i] = new Pthread(p);
            pthreads[i].setName("生产者"+(i+1));
            cthreads[i] = new Cthread(c);
            cthreads[i].setName("消费者"+(i+1));
            pthreads[i].start();
            cthreads[i].start();
        }
        Thread.sleep(5000);
        Thread[] threadArray = new Thread[Thread.currentThread().getThreadGroup().activeGroupCount()];
        Thread.currentThread().getThreadGroup().enumerate(threadArray);
        for (int i=0;i<threadArray.length;i++) {
            System.out.println(threadArray[i].getName()+" "+threadArray[i].getState());
        }
    }
}
