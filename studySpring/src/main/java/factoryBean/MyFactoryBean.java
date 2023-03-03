package factoryBean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import lookUp.User;

@Component
public class MyFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new User();
    }
 
    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}