package parse;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class LabelNameSpaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("label",new MyLabelBeanDefinitionParser());
    }
}