package event.events;

import event.IEvent;

public class StartClickedEvent implements IEvent {
    public final int DIM;
    public StartClickedEvent(int dim){
        DIM = dim;
    }
}
