package aop.proxyFactory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.annotation.AnnotationAwareAspectJAutoProxyCreator;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;

public class StaticMethodMatcherPointcutTest {

    public static void main(String[] args) throws NoSuchMethodException {
        StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {

            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(Transactional.class)) {
                    return true;
                }
                MergedAnnotations classAnnotations = MergedAnnotations.from(targetClass, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                if (classAnnotations.isPresent(Transactional.class)) {
                    return true;
                }
                return false;
            }
        };
        System.out.println(pointcut.matches(T1.class.getMethod("foo"), T1.class));
        System.out.println(pointcut.matches(T1.class.getMethod("bar"), T1.class));
        System.out.println(pointcut.matches(T2.class.getMethod("foo"), T2.class));
        System.out.println(pointcut.matches(T3.class.getMethod("foo"), T3.class));
    }

    static class T1 {
        @Transactional
        public void foo() {

        }

        public void bar() {

        }
    }

    static class T2 {
        public void foo() {

        }
    }

    @Transactional
    interface I3 {
        void foo();
    }

    static class T3 implements I3 {
        public void foo() {
        }
    }
}
