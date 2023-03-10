package lifeCycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.PriorityOrdered;

import org.springframework.stereotype.Component;

@Component
public class MyBeanProcess3 implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("MyBeanProcess3");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AbstractBeanDefinition beanDefinition2 = (AbstractBeanDefinition) beanFactory.getBeanDefinition("bean2");
        beanDefinition2.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}


