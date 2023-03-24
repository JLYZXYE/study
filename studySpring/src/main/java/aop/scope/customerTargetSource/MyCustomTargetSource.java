package aop.scope.customerTargetSource;

import org.springframework.aop.target.AbstractBeanFactoryBasedTargetSource;

import java.io.Serializable;

public class MyCustomTargetSource extends AbstractBeanFactoryBasedTargetSource implements Serializable {
	private static final long serialVersionUID = 1949093163787033304L;

	@Override
	public Object getTarget() throws Exception {
		System.out.println("MyCustomTargetSource...被调用了....");
		return getBeanFactory().getBean(this.getTargetBeanName());

	}
}
