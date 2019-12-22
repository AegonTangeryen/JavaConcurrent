package FakeDeath;

// 消费者 customer 获取值
public class C {
    private String lock;

    public C(String lock) {
        this.lock = lock;
    }

    public void getValue() {
        try {
            synchronized (lock) {
                while (ValueObject.value.equals("")) {
                    System.out.println("消费者"+Thread.currentThread().getName()+"   waiting ");
                    lock.wait();
                }
                System.out.println("消费者"+Thread.currentThread().getName()+"   runnable ");
                ValueObject.value = "";
                lock.notify();
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
