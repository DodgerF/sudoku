package event.events;

import event.IEvent;

public class StartClickedEvent implements IEvent {
    public final int DIR;
    public final String DIFFICULTY;
    public StartClickedEvent(int dir, String difficulty){
        DIR = dir;
        DIFFICULTY = difficulty;
    }
}
