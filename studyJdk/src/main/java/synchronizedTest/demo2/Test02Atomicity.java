package synchronizedTest.demo2;

import java.util.ArrayList;

/**
 * 案例演示:5个线程各执行1000次 i++;
 */
public class Test02Atomicity {
    private static int number = 0;
    private static Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable increment = () -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (object) {
                    number++;
                }

            }
        };
        ArrayList<Thread> ts = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(increment);
            t.start();
            ts.add(t);
        }
        for (Thread t : ts) {
            t.join();
        }
        System.out.println("number = " + number);
    }
}