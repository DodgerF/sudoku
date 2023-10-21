package controllers;

import objects.Grid;
import event.EventBus;
import event.IEvent;
import event.IEventListener;
import event.events.StartClickedEvent;

public class SudokuController<T extends IEvent> implements IEventListener<T> {
    @Override
    public void handle(T event) {
        if (event.getClass() == StartClickedEvent.class)
            test((StartClickedEvent) event);
    }
    private void test(StartClickedEvent event){
        Grid grid = new Grid(event.DIR);
        grid.checkGrid();
    }

    public void initialize() {
        SudokuController<StartClickedEvent> listener = new SudokuController<>();
        EventBus.getInstance().subscribe(StartClickedEvent.class, listener);
    }


}
