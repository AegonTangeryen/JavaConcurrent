package FakeDeath;

// 生产者 producer
public class P {
    private String lock;

    public P(String lo) {
        this.lock = lo;
    }

    public void setValue() {
        try {
            synchronized (lock) {
                // value不等于空，即还有商品时，生产商就不继续生产
                while (!ValueObject.value.equals("")) {
                    System.out.println("生产者"+Thread.currentThread().getName()+"   waiting ");
                    lock.wait();
                }
                System.out.println("生产者"+Thread.currentThread().getName()+"   runnable");
                String value = System.currentTimeMillis()+"^_^"+System.nanoTime();
                ValueObject.value = value;
                lock.notify();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
