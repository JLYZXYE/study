package aop.scope.targetClass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ScopeProxyBean2 {
    private ScopeProxyBean scopeProxyBean;

    @Autowired
    public void setScopeProxyBean(ScopeProxyBean scopeProxyBean) {
        this.scopeProxyBean = scopeProxyBean;
    }

    public ScopeProxyBean getScopeProxyBean() {
        return scopeProxyBean;
    }
}
