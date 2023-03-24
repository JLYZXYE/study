package aop.scope.targetClass;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ScopeTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Config.class);
        ScopeProxyBean2 bean2 = applicationContext.getBean(ScopeProxyBean2.class);
        System.out.println(applicationContext.getBean(Aspect1.class));
        for (int i = 0; i < 10; i++) {
            ScopeProxyBean scopeProxyBean = bean2.getScopeProxyBean();
            scopeProxyBean.code();
        }
    }
}
