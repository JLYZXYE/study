package lifeCycle;

import lifeCycle.bean.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();

        /**
         * 注入模式为byName或byType时 且有set方法 会自动注入 即使没有@Autowire标注
         * 这时如果ignoreDependencyType 对应类型 ignoreDependencyInterface中的相应set方法 不会自动被注入
         * @see AbstractAutowireCapableBeanFactory#populateBean
         *
         * 如果标注了@Autowire ignoreDependencyType ignoreDependencyInterface 相关bean还会被注入
         * @see AbstractAutowireCapableBeanFactory#populateBean#InstantiationAwareBeanPostProcessor#postProcessProperties
         */
        annotationConfigApplicationContext.getDefaultListableBeanFactory().ignoreDependencyType(Bean1.class);
        annotationConfigApplicationContext.getDefaultListableBeanFactory().ignoreDependencyInterface(AutoServiceAware.class);
        annotationConfigApplicationContext.register(Config.class);
        annotationConfigApplicationContext.refresh();
        Bean2 bean2 = (Bean2) annotationConfigApplicationContext.getBean("bean2");
        System.out.println(bean2);
    }
}
