package classloader;

import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public class TestCallerSensitive {

    /**
     * -Xbootclasspath:bootclasspath ：让jvm从指定的路径中加载bootclass，用来替换jdk的rt.jar。一般不会用到。
     *          -Xbootclasspath/a: path ： 被指定的文件追加到默认的bootstrap路径中。
     *          -Xbootclasspath/p: path ： 让jvm优先于默认的bootstrap去加载path中指定的class。
     * @param args
     */
    public static void main(String[] args) {
        TestCallerSensitive.withCallerSensitive();
        TestCallerSensitive.noCallerSensitive();
    }

    /*有CallerSensitive注解*/
    @CallerSensitive
    public static void withCallerSensitive() {
        System.out.format("Method is called by %s%n", Reflection.getCallerClass());
    }

    /*无注解直接调用*/
    public static void noCallerSensitive() {
        System.out.format("Method is called by %s%n",Reflection.getCallerClass());
    }

}
