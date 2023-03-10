package lifeCycle.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class Bean2 implements AutoServiceAware {

    public Bean1 bean1;

    public Bean3 bean3;

    public void setBean1(Bean1 bean1) {
        this.bean1 = bean1;
    }

    public Bean1 getBean1() {
        return bean1;
    }


    @Override
    public void setAutoBean(Bean3 bean3) throws BeansException {
        this.bean3 = bean3;
    }
}
