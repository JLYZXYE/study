package aop.scope.customerTargetSource;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.target.AbstractBeanFactoryBasedTargetSourceCreator;
import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.io.Serializable;

public class MyCustomTargetSourceCreator extends AbstractBeanFactoryBasedTargetSourceCreator {
	public MyCustomTargetSourceCreator() {
		System.out.println("MyCustomTargetSourceCreator....构造方法");
	}

	@Override
	protected AbstractBeanFactoryBasedTargetSource createBeanFactoryBasedTargetSource(Class<?> beanClass, String beanName) {
		System.out.println("MyCustomTargetSourceCreator---------->beanName="+beanName);
		if (getBeanFactory() instanceof ConfigurableListableBeanFactory) {
			BeanDefinition beanDefinition = ((ConfigurableListableBeanFactory) getBeanFactory()).getBeanDefinition(beanName);
			if (beanClass.isAssignableFrom(ScopeProxyBean.class)) {

				return new MyCustomTargetSource();
			}
		}
		return null;
	}
}

