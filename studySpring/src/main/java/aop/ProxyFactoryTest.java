package aop;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactoryTest {
    public static void main(String[] args) {
        // 切点
        AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
        aspectJExpressionPointcut.setExpression("execution(* eat())");

        MethodInterceptor advice = invocation -> {
            System.out.println("before...");
            Object result = invocation.proceed();
            System.out.println("after...");
            return result;
        };

        // 切面（通知）[切点+通知]
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(aspectJExpressionPointcut,advice);


        // 创建代理
        Man man = new Man();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(man);
        proxyFactory.setInterfaces(man.getClass().getInterfaces());
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvisor(defaultPointcutAdvisor);
        WoMan proxy = (WoMan) proxyFactory.getProxy();
        proxy.eat();



    }

    interface Person{
        void eat();
        void sleep();
    }

    static class Man implements Person{

        @Override
        public void eat() {
            System.out.println("man eat");
        }

        @Override
        public void sleep() {
            System.out.println("man sleep");
        }
    }

    static class WoMan {

        public void eat() {
            System.out.println("woman eat");
        }

        public void sleep() {
            System.out.println("woman sleep");
        }
    }
}
