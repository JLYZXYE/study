package proxy.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

class MyApiInterceptor2 implements MethodInterceptor {
 
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("吃饭前我会先洗手2"); // 此处可以做一些操作
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("吃完饭我会先休息会儿2" );  // 方法调用之后也可以进行一些操作
        return result;
    }
}