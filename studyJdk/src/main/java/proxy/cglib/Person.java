package proxy.cglib;

// 创建一个普通类做为代理类
class Person {
    //  代理类中由普通方法
    public void eat() {
        System.out.println("我要开始吃饭咯...");
    }
 
    public void play() {
        System.out.println("我要出去玩耍了,,,");
    }
}