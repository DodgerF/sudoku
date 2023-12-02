package objects;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Cell {
    private TextField _field;
    private Integer _value;
    private int _cordX;  private int _cordY;
    public Cell() {
        _field = new TextField();

        _field.setAlignment(Pos.CENTER);
    }
    public TextField getField() {
        return _field;
    }
    public void setDisable(boolean bool) {
        if (bool) {
            _field.setText(_value.toString());
        }
        _field.setDisable(bool);
    }

    public void setValue(Integer value) {
        if (value < 1) return;
        _value = value;
    }
    public Integer getValue() {
        return _value;
    }

    public void setCords(int x, int y) {
        _cordX = x;
        _cordY = y;
    }

    public int getCordX() {
        return _cordX;
    }

    public int getCordY() {
        return _cordY;
    }

}
