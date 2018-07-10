package PipedCom;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class Main {
    public static void main(String[] args) {
        try {
            WriteData writeData = new WriteData();
            ReadData readData = new ReadData();

            PipedInputStream inputStream = new PipedInputStream();
            PipedOutputStream outputStream = new PipedOutputStream();

            outputStream.connect(inputStream);
//            inputStream.connect(outputStream); // 也可以这样写
            ReadThread rt = new ReadThread(readData,inputStream);
            rt.start();
            Thread.sleep(2000); // 保证接收线程先开启
            WriteThread wt = new WriteThread(writeData,outputStream);
            wt.start();
        }catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
