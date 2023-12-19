package asm;

public class HelloWorld {
    public static void main(String[] args) {

        a();
        HelloWorld1 helloWorld1 = new HelloWorld1();
        helloWorld1.aa();

    }


    static int a() {
        int b = 1;
        int c = 2;
        int a = b + c;
        System.out.println("调用a方法" + a);
        return a;
    }
}
