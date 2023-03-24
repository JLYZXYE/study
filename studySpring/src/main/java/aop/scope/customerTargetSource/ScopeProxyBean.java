package aop.scope.customerTargetSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ScopeProxyBean {
	private ScopeProxyBean2 scopeProxyBean2;

	public ScopeProxyBean2 getScopeProxyBean2() {
		return scopeProxyBean2;
	}

	/**
	 * todo 存在依赖注入每次都会创建 MyCustomTargetSourceCreator
	 * @param scopeProxyBean2
	 */
	@Autowired
	public void setScopeProxyBean2(ScopeProxyBean2 scopeProxyBean2) {
		this.scopeProxyBean2 = scopeProxyBean2;
	}
	public void code() {
		System.out.println(this.hashCode());
	}
}