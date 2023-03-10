package observer.event;

import observer.event.Event;

public class SickEvent extends Event {

    public SickEvent(String msg) {
        this.msg = msg;
    }

    @Override
    public void doThing() {
        System.out.println("to relax");
    }
}
