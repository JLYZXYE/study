package annoation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

@org.springframework.stereotype.Component
@Order(1)
public class ComponentBImple implements Component {
   @Override
   public void print() {
      System.out.println("b");
   }

   public AutowireComponentBean autowireComponentBean;


   @Autowired
   public void setAutowireComponentBean(AutowireComponentBean autowireComponentBean) {
      this.autowireComponentBean = autowireComponentBean;
   }
}