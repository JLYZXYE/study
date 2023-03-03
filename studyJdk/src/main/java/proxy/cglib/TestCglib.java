package proxy.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

public class TestCglib {
    public static void main(String[] args) {
        // 设置拦截器
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(Person.class);
        // 设置回调数组
        enhancer.setCallbacks(new Callback[]{new MyApiInterceptor(),new MyApiInterceptor2()});
        // 设置具体使用的回调类
        enhancer.setCallbackFilter(new MyCallbackFilter());
        // 创建代理
        Person person = (Person) enhancer.create();

        // 执行代理逻辑
        person.eat();
    }
}