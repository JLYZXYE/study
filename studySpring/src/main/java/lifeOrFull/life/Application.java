package lifeOrFull.life;

import lifeOrFull.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 官方定义为：在没有标注@Configuration的类里面有@Bean方法就称为Lite模式的配置。透过源码再看这个定义是不完全正确的，而应该是有如下case均认为是Lite模式的配置类：
 *
 *
 *
 * 类上标注有@Component注解
 * 类上标注有@ComponentScan注解
 * 类上标注有@Import注解
 * 类上标注有@ImportResource注解
 * 若类上没有任何注解，但类内存在@Bean方法
 * 以上case的前提均是类上没有被标注@Configuration，在Spring 5.2之后新增了一种case也算作Lite模式：
 *
 * 标注有@Configuration(proxyBeanMethods = false)，注意：此值默认是true哦，需要显示改为false才算是Lite模式
 * 细心的你会发现，自Spring5.2（对应Spring Boot 2.2.0）开始，内置的几乎所有的@Configuration配置类都被修改为了@Configuration(proxyBeanMethods = false)，目的何为？答：以此来降低启动时间，为Cloud Native继续做准备
 *
 *
 * 优点：
 * 运行时不再需要给对应类生成CGLIB子类，提高了运行性能，降低了启动时间
 * 可以该配置类当作一个普通类使用喽：也就是说@Bean方法 可以是private、可以是final
 *
 * 缺点：
 * 不能声明@Bean之间的依赖，也就是说不能通过【方法调用】来依赖其它Bean
 * （其实这个缺点还好，很容易用其它方式“弥补”，比如：把依赖Bean放进方法入参里即可）
 */
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 配置类情况
        System.out.println(context.getBean(LiteConfig.class).getClass());
        System.out.println(context.getBean(LiteConfig.InnerConfig.class).getClass());

        String[] beanNames = context.getBeanNamesForType(User.class);
        for (String beanName : beanNames) {
            User user = context.getBean(beanName, User.class);
            System.out.println("beanName:" + beanName);
            System.out.println(user.getClass());
            System.out.println(user);
            System.out.println("------------------------");
        }
    }
}