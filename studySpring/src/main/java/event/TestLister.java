package event;

import org.springframework.context.ApplicationListener;

public class TestLister implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent event) {
        event.print();
    }
}
