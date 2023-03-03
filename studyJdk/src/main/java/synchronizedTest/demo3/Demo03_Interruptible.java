package synchronizedTest.demo3;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
目标:演示Lock不可中断和可中断
*/
public class Demo03_Interruptible {
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
 test01();
//        test02();
    }

    // 演示Lock可中断
    public static void test02() throws InterruptedException {
        Runnable run = () -> {
            String name = Thread.currentThread().getName();
            boolean b = false;
            try {
                b = lock.tryLock(3, TimeUnit.SECONDS);
                if (b) {
                    System.out.println(name + "获得锁,进入锁执行");
                    Thread.sleep(8888);
                } else {
                    System.out.println(name + "在指定时间没有得到锁做其他操作");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (b) {
                    lock.unlock();
                    System.out.println(name + "释放锁");
                }
            }
        };
        Thread t1 = new Thread(run);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run);
        t2.start();
// System.out.println("停止t2线程前");
// t2.interrupt();
// System.out.println("停止t2线程后");
//
// Thread.sleep(1000);
// System.out.println(t1.getState());
// System.out.println(t2.getState());
    }

    // 演示Lock不可中断
    public static void test01() throws InterruptedException {
        Runnable run = () -> {
            String name = Thread.currentThread().getName();
            try {
                lock.lock();
                System.out.println(name + "获得锁,进入锁执行");
                Thread.sleep(8888);
            } catch (InterruptedException e) {
//                小结
//                不可中断是指，当一个线程获得锁后，另一个线程一直处于阻塞或等待状态，前一个线程不释放锁，后
//                一个线程会一直阻塞或等待，不可被中断。
//                synchronized属于不可被中断
//                        Lock的lock方法是不可中断的
//                Lock的tryLock方法是可中断的
//                第五章：synchronized原理
//                javap 反汇编
//                目标
//                        通过javap反汇编学习synchronized的原理
//                我们编写一个简单的synchronized代码，如下：
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(name + "释放锁");
            }
        };
        Thread t1 = new Thread(run);
        t1.start();
        Thread.sleep(1000);
        Thread t2 = new Thread(run);
        t2.start();
        System.out.println("停止t2线程前");
        t2.interrupt();
        System.out.println("停止t2线程后");
        Thread.sleep(1000);
        System.out.println(t1.getState());
        System.out.println(t2.getState());
    }
}