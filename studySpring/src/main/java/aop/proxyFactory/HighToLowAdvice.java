package aop.proxyFactory;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectInstanceFactory;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.aspectj.AspectJMethodBeforeAdvice;
import org.springframework.aop.aspectj.SingletonAspectInstanceFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class HighToLowAdvice {

    static class Aspect {
        @Before("execution(* foo())")
        public void before1() {
            System.out.println("Aspect before1...");
        }

        @Before("execution(* foo())")
        public void before2() {
            System.out.println("before2...");
        }

        public void after() {
            System.out.println("after");
        }

        public void afterReturning() {
            System.out.println("afterReturning");
        }

        public void afterThrowing() {
            System.out.println("afterThrowing");
        }

        public Object around(ProceedingJoinPoint pjp) throws Throwable {
            try {
                System.out.println("around...before");
                return pjp.proceed();
            } finally {
                System.out.println("around...after");
            }
        }

        /**
         * 1.表达式解析创建切点
         * 2.依据注解类型创建对应通知
         * 3.创建低级切面
         *
         * 相关通知：
         * AspectJAfterAdvice
         * AspectJAfterReturningAdvice
         * AspectJAfterThrowingAdvice
         * AspectJAroundAdvice
         * AspectJMethodBeforeAdvice
         */
        public static void main(String[] args) {
            Method[] declaredMethods = Aspect.class.getDeclaredMethods();
            AspectInstanceFactory aif = new SingletonAspectInstanceFactory(new Aspect());
            for (Method declaredMethod : declaredMethods) {
                if (declaredMethod.isAnnotationPresent(Before.class)) {
                    String expression = declaredMethod.getAnnotation(Before.class).value();
                    AspectJExpressionPointcut aspectJExpressionPointcut = new AspectJExpressionPointcut();
                    aspectJExpressionPointcut.setExpression(expression);
                    AspectJMethodBeforeAdvice aspectJMethodBeforeAdvice = new AspectJMethodBeforeAdvice(declaredMethod, aspectJExpressionPointcut, aif);
                    DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor(aspectJExpressionPointcut, aspectJMethodBeforeAdvice);
                    System.out.println(defaultPointcutAdvisor);


                }
            }
        }

    }
}
