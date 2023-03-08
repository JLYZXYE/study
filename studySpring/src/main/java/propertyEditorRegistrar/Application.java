package propertyEditorRegistrar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext application = new AnnotationConfigApplicationContext();
        application.getDefaultListableBeanFactory().addPropertyEditorRegistrar(new LocalDateEditorRegistrar());
        application.registerBean(Person.class);
        application.refresh();
        Person person =  application.getBean(Person.class);
        System.out.println(person);
    }
}
