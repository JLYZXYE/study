package test;

import annoation.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class RegisterResolvableDependencyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("test.xml");
        AutowireComponentBean autowireComponentBean = context.getBean("autowireComponentBean", AutowireComponentBean.class);

        ComponentAImple componentAImple = context.getBean("componentAImple", ComponentAImple.class);
        ComponentBImple componentBImple = context.getBean("componentBImple", ComponentBImple.class);
//        AutowiredRequestController autowiredRequestController = context.getBean("autowiredRequestController", AutowiredRequestController.class);
        System.out.println("autowireComponentBean.getComponent() "+autowireComponentBean.getComponent());
        System.out.println("componentAImple "+componentAImple);
        System.out.println("componentBImple "+componentBImple);
//        System.out.println(autowiredRequestController);

    }

}
