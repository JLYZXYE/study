package aop.scope.customerTargetSource;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor, PriorityOrdered, BeanFactoryAware {

	private BeanFactory factory;

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof AbstractAutoProxyCreator) {
			System.out.println("postProcessAfterInitialization---------->beanName="+beanName);
			AbstractAutoProxyCreator creator = (AbstractAutoProxyCreator) bean;
			//AnnotationAwareAspectJAutoProxyCreator creator = (AnnotationAwareAspectJAutoProxyCreator) bean;
			// 注册 MyTargetSourceCreator
			MyCustomTargetSourceCreator customTargetSourceCreator = new MyCustomTargetSourceCreator();
			customTargetSourceCreator.setBeanFactory(factory);

			creator.setCustomTargetSourceCreators(customTargetSourceCreator);
		}

		/** 这里记得返回 */
		return bean;
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.factory = beanFactory;
	}

	@Override
	public int getOrder() {
		return 45;
	}
}
