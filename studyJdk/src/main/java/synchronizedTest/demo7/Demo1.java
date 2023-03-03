package synchronizedTest.demo7;

import org.openjdk.jol.info.ClassLayout;

public class Demo1 {

    public static void main(String[] args) {
        Mythread mythread = new Mythread();
        mythread.run();
//        Mythread mythread2 = new Mythread();
//        mythread2.run();
    }


}

class Mythread extends Thread {
    static Object object = new Object();

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            synchronized (object) {
                System.out.println(ClassLayout.parseInstance(object).toPrintable());
            }
        }
    }
}
