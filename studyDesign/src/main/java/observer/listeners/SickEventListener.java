package observer.listeners;

import observer.event.EatEvent;
import observer.event.SickEvent;

public class SickEventListener implements EventListener<SickEvent> {
    @Override
    public void handleEvent(SickEvent event) {
        System.out.println("SickEventListener handleEvent");
    }
}
