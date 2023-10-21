package objects;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Cell {
    private Integer _value;
    private TextField _field;
    private Label _textValue;
    private double cordX;  private double cordY;
    private static double width; private static double height;

    public Cell(Integer value) {
        _textValue = new Label();
        _field = new TextField();

        _value = value;
        _textValue.setText(_value.toString());
    }

    public void setVisible(boolean isVisible){
        _field.setVisible(!isVisible);
        _textValue.setVisible(isVisible);
    }
}
