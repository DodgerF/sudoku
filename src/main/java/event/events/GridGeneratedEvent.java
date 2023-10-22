package event.events;

import event.IEvent;
import objects.Grid;

public class GridGeneratedEvent implements IEvent {
    public final Grid GRID;
    public GridGeneratedEvent(Grid grid){
        GRID = grid;
    }
}
