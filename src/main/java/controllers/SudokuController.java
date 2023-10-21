package controllers;

import event.EventBus;
import event.IEvent;
import event.IEventListener;
import event.events.StartClickedEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class SudokuController<T extends IEvent> implements IEventListener<T> {
    @Override
    public void handle(T event) {
        Test();
    }
    private void Test(){
        System.out.println("its work");
    }

    public void initialize() {
        SudokuController<StartClickedEvent> listener = new SudokuController<>();
        EventBus.getInstance().subscribe(StartClickedEvent.class, listener);
    }


}
