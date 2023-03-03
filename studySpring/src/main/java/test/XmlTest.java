package test;

import replace.ChangeMethod;
import lookUp.LookUp;
import qualifier.Product;
import lookUp.User;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import parse.Label;

public class XmlTest {

    public static void main(String[] args) {
        // create and configure beans
        XmlBeanFactory context = new XmlBeanFactory(new ClassPathResource("test.xml"));
        Product productService = context.getBean("product", Product.class);
        System.out.println(productService);
        // retrieve configured instance
        User userService = context.getBean("user", User.class);
        LookUp lookUp = context.getBean("lookUp", LookUp.class);
        ChangeMethod changeMethod = context.getBean("changeMethod", ChangeMethod.class);
        BeanDefinition beanDefinition =  context.getBeanDefinition("user");
        System.out.println(beanDefinition.getAttribute("a1"));
        System.out.println(userService);
        Label userLabel = userService.getUserLabel();
        System.out.println(userLabel);

        lookUp.ask();
        changeMethod.test();

        Label label = context.getBean("label", Label.class);
        System.out.println(label);

        context.registerBeanDefinition("我要再注册一个bean",new GenericBeanDefinition());
    }
}
