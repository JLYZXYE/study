package autoconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * 1.把A_SonConfig和B_ParentConfig挪动到Application扫描不到的包内，切记：一定且必须是扫描不到的包内
 * 2.当前工程里增加配置META-INF/spring.factories，内容为(配置里Son和Parent前后顺序对结果无影响)
 * org.springframework.boot.autoconfigure.EnableAutoConfiguration=autoconfiguration.A_SonConfig,autoconfiguration.B_ParentConfig
 *
 * 总结：若你不用@AutoConfigureBefore这个注解，单单就想依赖于spring.factories里的先后顺序的来控制实际的加载顺序，答案是不可以，控制不了
 * 例子中有个小细节：我每次都故意输出了我是被自动扫描的配置，初始化啦....这句话，可以发现被扫描进去配置实例化是在它前面（见错误示例），而通过spring.factories方式进去是在它的后面（见正确姿势）
 * 从这个小细节可以衍生得到结论：Spring Boot的自动配置均是通过spring.factories来指定的，它的优先级最低（执行时机是最晚的）；通过扫描进来的一般都是你自己自定义的配置类，所以优先级是最高的，肯定在自动配置之前加载
 * 从这你应该学到：若你要指定扫描的包名，请千万不要扫描到形如org.springframework这种包名，否则“天下大乱”（当然喽为了防止这种情况出现，Spring Boot做了容错的。它有一个类专门检测这个case防止你配置错了，具体参见ComponentScanPackageCheck默认实现）
 * 请尽量不要让自动配置类既被扫描到了，又放在spring.factories配置了，否则后者会覆盖前者，很容易造成莫名其妙的错误
 */
@SpringBootApplication
public class TrueExample {
    public static void main(String[] args) {
        SpringApplication.run(TrueExample.class, args).close();
    }

    @Configuration
    public class C_DemoConfig {
        public C_DemoConfig(){
            System.out.println("我是被自动扫描的配置，初始化啦....");
        }
    }
}
