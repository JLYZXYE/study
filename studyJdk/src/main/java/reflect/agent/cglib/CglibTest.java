package reflect.agent.cglib;


import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibTest {

    public static void main(String[] args) {
        Proxy proxy = new Proxy();
        Target target = new Target();
        proxy.setMethodInterceptor(new MethodInterceptor() {
            @Override
            public Object intercept(Object p, Method method, Object[] args,
                                    MethodProxy methodProxy) throws Throwable {
                System.out.println("before...");
                return method.invoke(target, args); // 反射调用
                // FastClass
//                return methodProxy.invoke(target, args); // 内部无反射, 结合目标用 会调用TargetFastClass的getIndex然后调用TargetFastClass的invoke
//                return methodProxy.invokeSuper(p, args); // 内部无反射, 结合代理用 会调用ProxyFastClass的getIndex然后调用ProxyFastClass的invoke
            }
        });

        proxy.save();
        proxy.save(1);
        proxy.save(2L);
    }
}
