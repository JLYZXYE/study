package autoconfiguration;

import org.springframework.context.annotation.Configuration;

@Configuration
public class A_SonConfig {

    A_SonConfig() {
        System.out.println("配置类SonConfig构造器被执行...");
    }
}