package aop.scope.customerTargetSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ScopeProxyBean2 {
	private ScopeProxyBean scopeProxyBean;

	public ScopeProxyBean getScopeProxyBean() {
		return scopeProxyBean;
	}

	@Autowired
	public void setScopeProxyBean(ScopeProxyBean scopeProxyBean) {
		this.scopeProxyBean = scopeProxyBean;
	}
}