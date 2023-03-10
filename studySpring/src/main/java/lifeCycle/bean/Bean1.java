package lifeCycle.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class Bean1 implements InitializingBean, DisposableBean {

    public Bean1() {
        System.out.println("Bean1构造");
    }

    @Autowired
    public void autoWire() {
        System.out.println("依赖注入:@Autowired");
    }


    @Resource
    public void resource(Bean1 bean1) {
        System.out.println("依赖注入:@Resource");
    }


    @Autowired
    public void version(@Value("${java.version}") String version) {
        System.out.println("依赖注入:@Value");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化之前： InitializingBean#afterPropertiesSet");
    }


    @Override
    public void destroy() throws Exception {
        System.out.println("销毁之前：DisposableBean#destroy");
    }
}
