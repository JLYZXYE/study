package annoation;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AutowireComponentBean {
    public annoation.Component component;

    public annoation.Component getComponent() {
        return component;
    }

//    @Autowired
//    public void setComponent(spring.annoation.Component component) {
//        this.component = component;
//    }

    @Autowired
    public void setComponent(ObjectProvider<annoation.Component> component) {
        annoation.Component component1 = component.orderedStream().findFirst().orElse(null);
        this.component = component1;;
    }
}
