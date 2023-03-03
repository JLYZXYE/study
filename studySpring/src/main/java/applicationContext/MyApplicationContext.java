package applicationContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyApplicationContext extends ClassPathXmlApplicationContext {


    public MyApplicationContext(String configLocation) throws BeansException {
        super(configLocation);
    }

    @Override
    protected void initPropertySources(){
        getEnvironment().setRequiredProperties("aaa");
    }


    @Override
    protected void customizeBeanFactory(DefaultListableBeanFactory beanFactory) {
        super.setAllowBeanDefinitionOverriding(false);
        super.setAllowCircularReferences(false);
        super.customizeBeanFactory(beanFactory);
    }
}
