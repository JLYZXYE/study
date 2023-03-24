package aop.scope.targetClass;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect // 高级切面类
@Order(1)
@Component
class Aspect1 {
    @Before("execution(* code())")
    public void before1() {
        System.out.println("aspect1 before1...");
    }

    @Before("execution(* code())")
    public void before2() {
        System.out.println("aspect1 before2...");
    }
}