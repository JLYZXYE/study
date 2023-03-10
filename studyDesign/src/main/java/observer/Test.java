package observer;

import observer.event.EatEvent;
import observer.listeners.EatEventListener;
import observer.listeners.SickEventListener;
import observer.publish.GenericPublisher;

public class Test {

    public static void main(String[] args) {
        EatEvent eatEvent = new EatEvent("水果");
        GenericPublisher genericPublisher = new GenericPublisher();
        genericPublisher.getEventMulticaster().addListeners(new EatEventListener());
        genericPublisher.getEventMulticaster().addListeners(new SickEventListener());
        genericPublisher.publishEvent(eatEvent);
    }
}
