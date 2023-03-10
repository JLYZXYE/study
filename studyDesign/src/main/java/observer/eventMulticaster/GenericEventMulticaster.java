package observer.eventMulticaster;

import observer.event.Event;
import observer.listeners.EventListener;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

public class GenericEventMulticaster implements EventMulticaster {


    private Set<EventListener> eventListeners = new HashSet<>();

    @Override
    public void addListeners(observer.listeners.EventListener eventListener) {
        eventListeners.add(eventListener);
    }

    @Override
    public void multicastEvent(Event event) {
        for (EventListener eventListener : eventListeners) {
            boolean match = matchEventType(event, eventListener);
            if (match) {
                eventListener.handleEvent(event);
            }

        }

    }

    private boolean matchEventType(Event event, EventListener eventListener) {
        // todo 优化泛型判断
        Type[] types = eventListener.getClass().getGenericInterfaces();
        Type type = types[0];
        ParameterizedType parameterizedType = (ParameterizedType) type;
        Type actualTypeArgument = parameterizedType.getActualTypeArguments()[0];
        String typeName = actualTypeArgument.getTypeName();
        String eventTypeName = event.getClass().getTypeName();
        return typeName.equals(eventTypeName);
    }
}
