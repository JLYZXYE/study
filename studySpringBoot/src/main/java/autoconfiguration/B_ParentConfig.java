package autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

@AutoConfigureBefore(A_SonConfig.class)
@Configuration
public class B_ParentConfig {

    B_ParentConfig() {
        System.out.println("配置类ParentConfig构造器被执行...");
    }
}