package message;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Locale;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);

        /**
         * MessageSourceFromDb
         */
        System.out.println(context.getMessage("desc", null, Locale.CHINA));
        System.out.println(context.getMessage("desc", null, Locale.UK));

        /**
         * ResourceBundleMessageSource/ReloadableResourceBundleMessageSource
         */
//        //未指定Locale，此时系统会取默认的locale对象，本地默认的值中文【中国】，即：zh_CN
//        System.out.println(context.getMessage("name", null, null));
//        System.out.println(context.getMessage("name", null, Locale.CHINA)); //CHINA对应：zh_CN
//        System.out.println(context.getMessage("personal_introduction", new String[]{"spring高手", "java高手"}, Locale.CHINA));
//        System.out.println(context.getMessage("name", null, Locale.UK)); //UK对应en_GB
//        System.out.println(context.getMessage("personal_introduction", new String[]{"spring", "java"}, Locale.UK)); //UK对应en_GB

    }
}
