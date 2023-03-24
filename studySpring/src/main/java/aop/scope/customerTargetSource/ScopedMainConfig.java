package aop.scope.customerTargetSource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 演示 ScopeProxy
 */
@Configuration
@ComponentScan(basePackages = {"aop.scope.customerTargetSource"})
@EnableAspectJAutoProxy
public class ScopedMainConfig {
}





