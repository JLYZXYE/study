package circularDependency;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args)  {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Config.class);
        A a = annotationConfigApplicationContext.getBean("a", A.class);
//        B b = annotationConfigApplicationContext.getBean("b", B.class);
//        System.out.println("a :" + a);
//        System.out.println("b:" + b);
        System.out.println("a.b:" + a.getB());
    }
}
