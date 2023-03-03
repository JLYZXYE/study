package annoation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class RegisterResolvableDependency implements BeanFactoryPostProcessor {


   @Override
   public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
//       Object componentBImple = beanFactory.getBean("componentBImple");
//       beanFactory.registerResolvableDependency(spring.annoation.Component.class, componentBImple);

   }
}


