package lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.*;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

@Component
public class MyBeanProcess implements BeanDefinitionRegistryPostProcessor, BeanFactoryPostProcessor, InstantiationAwareBeanPostProcessor, BeanPostProcessor, DestructionAwareBeanPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanProcess");
        System.out.println("bean注册：BeanDefinitionRegistryPostProcessor#postProcessBeanDefinitionRegistry。 使用场景mybatis整合spring");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("bean工厂准备完成：BeanFactoryPostProcessor#postProcessBeanFactory");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if (beanName.equals("bean1")) {
            System.out.println("实例化之前：InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation");

        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (beanName.equals("bean1")) {
            System.out.println("实例化之后：InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws
            BeansException {
        if (beanName.equals("bean1")) {
            System.out.println("依赖注入：InstantiationAwareBeanPostProcessor#postProcessProperties 常用注解：@Autowired @Value @Resource");
        }
        return null;
    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("bean1")) {
            System.out.println("初始化之前：BeanPostProcessor#postProcessBeforeInitialization");
        }
        return null;
    }


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("bean1")) {
            System.out.println("初始化之后：BeanPostProcessor#postProcessBeforeInitialization");
        }
        return null;
    }


    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals("bean1")) {
            System.out.println("销毁之前：DestructionAwareBeanPostProcessor#postProcessBeforeDestruction");
        }
    }

}


