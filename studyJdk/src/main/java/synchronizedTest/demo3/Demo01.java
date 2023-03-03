package synchronizedTest.demo3;

/*
可重入特性
可重入原理
synchronized的锁对象中有一个计数器（recursions变量）会记录线程获得几次锁.
可重入的好处
1. 可以避免死锁
2. 可以让我们更好的来封装代码
小结
synchronized是可重入锁，内部锁对象中会有一个计数器记录线程获取几次锁啦，在执行完同步代码块
时，计数器的数量会-1，知道计数器的数量为0，就释放这个锁。
不可中断特性
目标
学习synchronized不可中断特性
学习Lock的可中断特性
什么是不可中断
一个线程获得锁后，另一个线程想要获得锁，必须处于阻塞或等待状态，如果第一个线程不释放锁，第
二个线程会一直阻塞或等待，不可被中断。
synchronized不可中断演示
指的是同一个线程获得锁之后，可以直接再次获取该锁。
*/
public class Demo01 {
    public static void main(String[] args) {
        Runnable sellTicket = new Runnable() {
            @Override
            public void run() {
                synchronized (Demo01.class) {
                    System.out.println("我是run");
                    test01();
                }
            }

            public void test01() {
                synchronized (Demo01.class) {
                    System.out.println("我是test01");
                }
            }
        };
        new Thread(sellTicket).start();
        new Thread(sellTicket).start();
    }
}