package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import lookUp.LookUp;
import parse.Label;
import replace.ChangeMethod;
import qualifier.Product;
import lookUp.User;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous StaticMethodMatcherPointcutTest :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void test03() {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = AppTest.class.getClassLoader();
        System.out.println("classLoader = " + classLoader);
        //调用系统类加载器的getParent()：获取扩展类加载器
        ClassLoader classloader02 = classLoader.getParent();
        System.out.println("classloader02 = " + classloader02);
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载java的核心类库，无法加载自定义类的。
        ClassLoader classloader03 = classloader02.getParent();
        System.out.println("classloader03 = " + classloader03);
    }

    @Test
    public void test() {
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
