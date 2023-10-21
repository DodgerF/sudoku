package controllers;

import event.EventBus;
import event.events.StartClickedEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ViewController implements Initializable
{
    @FXML
    private Button _startButton;
    @FXML
    private ComboBox<String> _difficultyComboBox;
    @FXML
    private TextField _dimTextField;
    @FXML
    private Pane _pane;

    public void onStartClicked(){
        EventBus.getInstance().invoke(new StartClickedEvent());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> difficulty = new ArrayList<>();
        difficulty.add("Easy"); difficulty.add("Normal"); difficulty.add("Hard");
        _difficultyComboBox.getItems().addAll(difficulty);
    }
}
