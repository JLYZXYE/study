package observer.event;

public class EatEvent extends Event {

    public EatEvent(String msg) {
        this.msg = msg;
    }

    @Override
    public void doThing() {
        System.out.println("to eat");
    }
}
