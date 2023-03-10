package event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class TestLister2 implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("TestLister2 ApplicationEvent");
    }
}
