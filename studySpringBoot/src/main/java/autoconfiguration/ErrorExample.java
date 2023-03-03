package autoconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class ErrorExample {
    public static void main(String[] args) {
        SpringApplication.run(ErrorExample.class, args).close();
    }

//    /**
//     * @AutoConfigureBefore注解无效
//     */
//    @AutoConfigureBefore(A_SonConfig.class)
//    @Configuration
//    public class B_ParentConfig {
//
//        B_ParentConfig() {
//            System.out.println("配置类ParentConfig构造器被执行...");
//        }
//    }
//
//    @Configuration
//    public class A_SonConfig {
//
//        A_SonConfig() {
//            System.out.println("配置类SonConfig构造器被执行...");
//        }
//    }
//
//    @Configuration
//    public class C_DemoConfig {
//        public C_DemoConfig(){
//            System.out.println("我是被自动扫描的配置，初始化啦....");
//        }
//    }
}
