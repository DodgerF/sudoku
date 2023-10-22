package controllers;

import event.EventBus;
import event.IEvent;
import event.IEventListener;
import event.events.GridGeneratedEvent;
import event.events.StartClickedEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController<T extends IEvent> implements IEventListener<T>, Initializable
{
    @FXML
    private ComboBox<String> _dimensionComboBox;
    @FXML
    private Pane _pane;

    public void onStartClicked() {
        String dim = "";
        for (int i = 0; i < _dimensionComboBox.getValue().length(); i++) {
            if (_dimensionComboBox.getValue().charAt(i) == 'x') break;

            dim += _dimensionComboBox.getValue().charAt(i);
        }
        EventBus.getInstance().invoke(new StartClickedEvent((int)Math.sqrt(Integer.parseInt(dim))));
    }

    @Override
    public void handle(T event) {
        if (event.getClass() == GridGeneratedEvent.class) {
            OnGenerated( (GridGeneratedEvent) event);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> dimension = new ArrayList<>();
        dimension.add("9x9"); dimension.add("16x16"); dimension.add("25x25");
        _dimensionComboBox.getItems().addAll(dimension);
        _dimensionComboBox.setValue("9x9");
    }

    private void OnGenerated(GridGeneratedEvent event) {
        GridView _grid = new GridView(event.GRID);

        _pane.getChildren().clear();
        _grid.drawOnParent(_pane);
    }
}
