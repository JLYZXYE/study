package qualifier;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import lookUp.User;

public class QualifierTest {

    public static void main(String[] args) {
        // create and configure beans
        XmlBeanFactory context = new XmlBeanFactory(new ClassPathResource("test.xml"));
        Product productService = context.getBean("product", Product.class);
        System.out.println(productService);
        // retrieve configured instance
        User userService = context.getBean("user", User.class);
        System.out.println(userService);
    }
}
