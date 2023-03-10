package message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MainConfig {
    /**
     * 1.创建国际化文件
     * 2.向容器中注册一个MessageSource类型的bean，bean名称必须为：messageSource
     * @see AbstractApplicationContext#initMessageSource
     * 3.调用AbstractApplicationContext中的getMessage来获取国际化信息，其内部将交给第二步中注册的messageSource名称的bean进行处理
     *
     */
//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource result = new ResourceBundleMessageSource();
//        //可以指定国际化化配置文件的位置，格式：路径/文件名称,注意不包含【语言_国家.properties】含这部分
//        result.setBasenames("messages/info");
//        result.setDefaultEncoding("UTF-8");
//        return result;
//    }

    /**
     * setCacheMillis(long cacheMillis)
     * -1：表示永远缓存
     * <p>
     * 0：每次获取国际化信息的时候，都会重新读取国际化文件
     * <p>
     * 大于0：上次读取配置文件的时间距离当前时间超过了这个时间，重新读取国际化文件
     */
//    @Bean
//    public MessageSource messageSource() {
//        ReloadableResourceBundleMessageSource result = new ReloadableResourceBundleMessageSource();
//        result.setBasenames("messages/info");
//        //设置缓存时间1000毫秒
//        result.setCacheMillis(1000);
//        return result;
//    }


    @Bean
    public MessageSource messageSource() {
        MessageSourceFromDb messageSourceFromDb = new MessageSourceFromDb();
        return messageSourceFromDb;
    }


}
