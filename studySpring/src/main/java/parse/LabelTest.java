package parse;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LabelTest {

    public static void main(String[] args) {
        // create and configure beans
        XmlBeanFactory context = new XmlBeanFactory(new ClassPathResource("test.xml"));
        Label label = context.getBean("label", Label.class);
        System.out.println(label);
    }
}
