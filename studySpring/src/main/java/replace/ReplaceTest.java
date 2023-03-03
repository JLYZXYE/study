package replace;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class ReplaceTest {

    public static void main(String[] args) {
        // create and configure beans
        XmlBeanFactory context = new XmlBeanFactory(new ClassPathResource("test.xml"));
        ChangeMethod changeMethod = context.getBean("changeMethod", ChangeMethod.class);
        changeMethod.test();
    }
}
