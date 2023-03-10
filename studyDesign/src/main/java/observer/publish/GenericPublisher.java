package observer.publish;

import observer.event.Event;
import observer.eventMulticaster.EventMulticaster;
import observer.eventMulticaster.GenericEventMulticaster;

public class GenericPublisher implements Publisher {
    private final EventMulticaster eventMulticaster = new GenericEventMulticaster();

    public EventMulticaster getEventMulticaster() {
        return eventMulticaster;
    }

    @Override
    public void publishEvent(Event event) {
        //广播器去通知
        eventMulticaster.multicastEvent(event);
    }
}
