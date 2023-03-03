package factoryBean;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import lookUp.User;

public class FactoryBeanTest {
    public static void main(String[] args) {
        //获取BeanDefinition对象
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //简单的创建一个Bean
        beanDefinition.setBeanClass(MyFactoryBean.class);
        AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext();
        //将BeanDefinition创建到容器对象里面去
        applicationContext1.registerBeanDefinition("user",beanDefinition);
        applicationContext1.refresh();
        //注意：此时我们注册进Spring中的Bean有些特殊，他实现了两个实际上创建了两个Bean对象，第一个就是我们的FactoryBean_teat,
        //还有一个就是我们的User对象
        //此时id:&user表示的是FactoryBean_teat，id:user表示的是User对象。
        //获取生产出来的对象
        User user1 = applicationContext1.getBean("user", User.class);
        //获取工厂对象
        MyFactoryBean factoryBean_test = applicationContext1.getBean("&user", MyFactoryBean.class);
    }
}
