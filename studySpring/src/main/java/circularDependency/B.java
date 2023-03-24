package circularDependency;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class B implements InitializingBean {
    private A a;

    public A getA() {
        return a;
    }

    @Autowired
    @Lazy
    public void setA(A a) {
        this.a = a;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("b init");
    }
}
