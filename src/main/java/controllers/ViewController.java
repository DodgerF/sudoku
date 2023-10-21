package controllers;

import event.EventBus;
import event.events.StartClickedEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
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
    private Integer _dim;
    private final String _errorText = "The field size must be a number no less than 3 and no more than 9!";

    public void onStartClicked() {
        if (checkFields()){
            EventBus.getInstance().invoke(new StartClickedEvent(_dim, _difficultyComboBox.getValue()));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> difficulty = new ArrayList<>();
        difficulty.add("Easy"); difficulty.add("Normal"); difficulty.add("Hard");
        _difficultyComboBox.getItems().addAll(difficulty);

        _dimTextField.setText("3");
        _difficultyComboBox.setValue("Easy");
    }

    private boolean checkFields() {
        try {
            _dim = Integer.parseInt(_dimTextField.getText());
            if (_dim < 3 || _dim > 9) {
                showError(_errorText);
                return false;
            }
            return true;
        }
        catch (Exception e){
            showError(e.getMessage());
            return false;
        }
    }
    private void showError(String errorText) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(errorText);
        error.setHeaderText("");
        error.show();
    }
}
