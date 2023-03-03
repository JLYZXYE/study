package lookUp;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class LookUpTest {

    public static void main(String[] args) {
        // create and configure beans
        XmlBeanFactory context = new XmlBeanFactory(new ClassPathResource("test.xml"));
        LookUp lookUp = context.getBean("lookUp", LookUp.class);
        lookUp.ask();
    }
}
