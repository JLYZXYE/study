package event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PublishTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("test.xml");
        TestEvent testEvent = new TestEvent("hello","msg");
        applicationContext.publishEvent(testEvent);
    }
}
