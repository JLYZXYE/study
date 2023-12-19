import org.openjdk.jol.info.ClassLayout;

public class A {
    private Object object = new Object();

    public static void main(String[] args) {
        //打印出对象的内存布局
        System.out.println(ClassLayout.parseInstance(new A()).toPrintable());
    }
}