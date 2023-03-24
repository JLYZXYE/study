package circularDependency;

import lifeCycle.bean.Bean2;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class A implements InitializingBean {
    private B b;
    private C c;

    public C getC() {
        return c;
    }

    public B getB() {
        return b;
    }

    @Autowired
    @Lazy
    public void setBC(B b, C c) {
        this.b = b;
        this.c = c;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("a init");
    }
}
