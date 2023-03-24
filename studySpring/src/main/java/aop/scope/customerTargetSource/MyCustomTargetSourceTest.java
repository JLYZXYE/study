package aop.scope.customerTargetSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyCustomTargetSourceTest {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ScopedMainConfig.class);

        /**
         * 存在依赖注入情况下每次调用
         */
        ScopeProxyBean2 bean2 = context.getBean(ScopeProxyBean2.class);
        for (int i = 0; i < 10; i++) {
            bean2.getScopeProxyBean().code();
        }

    }

}
