package annoation;

import org.springframework.core.annotation.Order;

@org.springframework.stereotype.Component
@Order(2)
public class ComponentAImple implements Component {

   @Override
   public void print() {
      System.out.println("a");
   }
}




