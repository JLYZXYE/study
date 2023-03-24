package circularDependency;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class C implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("c init");
    }
}
