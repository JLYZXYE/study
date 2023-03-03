package lifeOrFull.life;

import lifeOrFull.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
// @Configuration(proxyBeanMethods = false) // 这样也是Lite模式
public class LiteConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setName("A哥-lite");
        user.setAge(18);
        return user;
    }


    @Bean
    private final User user2() {
        User user = new User();
        user.setName("A哥-lite2");
        user.setAge(18);

        // 模拟依赖于user实例 看看是否是同一实例
        System.out.println("user2调用user():"+System.identityHashCode(user()));
        System.out.println("user2调用user():"+System.identityHashCode(user()));

        return user;
    }

    public static class InnerConfig {

        @Bean
        // private final Son userInner() { // 只在lite模式下才好使
        public User userInner() {
            User user = new User();
            user.setName("A哥-lite-inner");
            user.setAge(18);
            return user;
        }
    }
}