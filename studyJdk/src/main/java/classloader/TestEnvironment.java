package classloader;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * 启动类加载器Bootstrap ClassLoader:加载JRE_HOME/lib下的核心包，该类加载器是用c++写的。
 * 扩展类加载器Extension ClassLoader:加载JRE_HOME/lib/ext目录下的扩展包,也可以通过启动参数-Djava.ext.dirs指定，该类用java编写。对应ExtClassLoader类
 * 应用类加载器Application ClassLoader:应用类加载器，加载classpath下的字节码文件，用java编写，对应AppClassLoader这个类，可以通过ClassLoader类的静态方法getSystemClassLoader()获得，所以又叫系统类加载器
 * 自定义类加载器：自定义的类加载器，通过直接或者间接继承抽象的ClassLoader类。
 */
public class TestEnvironment {
    public static void main(String[] args) {
        //启动类加载器
        System.out.println("1"+ System.getProperty("sun.boot.class.path"));
        //扩展类加载器
        System.out.println("2"+ System.getProperty("java.ext.dirs"));
        //应用类加载器
        System.out.println("3"+ System.getProperty("java.class.path"));

        URL[] urls = ((URLClassLoader) ClassLoader.getSystemClassLoader()).getURLs();
        for (URL url : urls) {
            System.out.println("4"+url);
        }
    }
}

