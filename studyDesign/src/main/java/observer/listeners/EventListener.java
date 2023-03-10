package observer.listeners;

import com.apple.eawt.ApplicationEvent;
import observer.event.Event;

public interface EventListener<E extends Event> {
    public void handleEvent(E event);
}
