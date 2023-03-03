package lifeOrFull.full;

import lifeOrFull.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FullConfig implements BeanFactoryAware {


    @Bean
    public User user() {
        User user = new User();
        user.setName("A哥-lite");
        user.setAge(18);
        return user;
    }


    @Bean
    User user2() {
        User user = new User();
        user.setName("A哥-lite2");
        user.setAge(18);

        // 模拟依赖于user实例 看看是否是同一实例
        System.out.println(System.identityHashCode(user()));
        System.out.println(System.identityHashCode(user()));

        return user;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("实现了BeanFactoryAware");
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