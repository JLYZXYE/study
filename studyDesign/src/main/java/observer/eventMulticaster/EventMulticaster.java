package observer.eventMulticaster;

import observer.event.Event;
import observer.listeners.EventListener;

public interface EventMulticaster {

    void addListeners(EventListener eventListener);

    void multicastEvent(Event event);
}
