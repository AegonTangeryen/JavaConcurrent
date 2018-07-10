package StartAndStop;

public class ByThread extends Thread {
    private int i;

    public ByThread(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        try {
            for (int j = this.i;j<50000;j++) {
                if (this.isInterrupted()) {
                    System.out.println("byThread已经是停止状态了，我要退出了");
                    throw new InterruptedException();
//                    return;
                }
                System.out.println("j= "+(j));
            }
            System.out.println("我在for外面");
        }catch (InterruptedException e) {
            System.out.println("进入ByThread类的catch里面");
            e.printStackTrace();
        }

    }
}
