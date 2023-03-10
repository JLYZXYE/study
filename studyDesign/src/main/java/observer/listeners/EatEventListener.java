package observer.listeners;

import observer.event.EatEvent;

public class EatEventListener implements EventListener<EatEvent> {
    @Override
    public void handleEvent(EatEvent event) {
        System.out.println("EatEventListener handleEvent");
    }
}
