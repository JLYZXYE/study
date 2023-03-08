package lifeCycle;

import lifeCycle.bean.Bean1;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(Bean1.class);
        annotationConfigApplicationContext.register(MyBeanProcess.class);
        annotationConfigApplicationContext.refresh();
        System.out.println(annotationConfigApplicationContext.getBean("bean1"));
    }
}
