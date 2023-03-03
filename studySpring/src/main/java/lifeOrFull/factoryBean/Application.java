package lifeOrFull.factoryBean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        SonFactoryBean sonFactoryBean = context.getBean("&son", SonFactoryBean.class);
        System.out.println("Spring容器内的SonFactoryBean：" + sonFactoryBean.hashCode());
        System.out.println("Spring容器内的SonFactoryBean identityHashCode：" + System.identityHashCode(sonFactoryBean));
        System.out.println("Spring容器内的SonFactoryBean：" + sonFactoryBean.getClass());

        System.out.println("Spring容器内的Son：" + context.getBean("son").hashCode());
    }
}