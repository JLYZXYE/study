package customer;

import org.springframework.beans.factory.xml.DefaultBeanDefinitionDocumentReader;
import org.w3c.dom.Element;

public class CustomerBeanReader extends DefaultBeanDefinitionDocumentReader {

    @Override
    protected void preProcessXml(Element root) {
        System.out.println(root);
        super.preProcessXml(root);
    }
}
