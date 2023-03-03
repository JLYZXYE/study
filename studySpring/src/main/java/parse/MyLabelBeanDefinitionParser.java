package parse;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class MyLabelBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
 
    @Override
    protected Class<?> getBeanClass(Element element) {
        return Label.class;
    }
 
    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        //将标签中所有的属性返回；
        String name = element.getAttribute("name");
        String id = element.getAttribute("id");
        Double height = Double.valueOf(element.getAttribute("height"));
        String sex = element.getAttribute("sex");
        if (StringUtils.hasText(name)){
            builder.addPropertyValue("name",name);
        }
        builder.addPropertyValue("id",id);
        builder.addPropertyValue("height",height);
 
        if (StringUtils.hasText(sex)){
            builder.addPropertyValue("sex",sex);
        }
    }
}