package observer.publish;

import observer.event.Event;

public interface Publisher {

    public void publishEvent(Event event);
}
