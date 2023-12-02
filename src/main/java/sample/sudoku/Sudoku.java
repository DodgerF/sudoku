package sample.sudoku;

import event.events.GridGeneratedEvent;
import objects.Grid;
import event.EventBus;
import event.IEvent;
import event.IEventListener;
import event.events.StartClickedEvent;

import java.util.Random;

public class Sudoku<T extends IEvent> implements IEventListener<T> {

    private Grid _grid;
    @Override
    public void handle(T event) {
        if (event.getClass() == StartClickedEvent.class)
            onStarted((StartClickedEvent) event);
    }
    private void onStarted(StartClickedEvent event){
        _grid = new Grid(event.DIM);
        disableCell(event.DIM);

        EventBus.getInstance().invoke(new GridGeneratedEvent(_grid));
    }

    private void disableCell(int dim) {
        int iterationAmount = (int) Math.pow(dim, 4) / 2;

        while (iterationAmount >= 0){
            _grid.massive[new Random().nextInt(_grid.getDim() * _grid.getDim())][new Random().nextInt(_grid.getDim() * _grid.getDim())].setDisable(true);
            iterationAmount--;
        }
    }
}
