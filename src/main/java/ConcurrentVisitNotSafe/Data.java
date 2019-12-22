package ConcurrentVisitNotSafe;

public class Data {
    private int num = 0;
    public void setNum(String username) {
        try {
            if (username.equals("a")) {
                num = 100;
                System.out.println("it is a who set the num");
                Thread.sleep(1000); // 人为制造一个延时，等待别的线程进来
            } else {
                num = 200;
                System.out.println("it is b who set the num");
            }
            System.out.println(username+" num= "+num);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
