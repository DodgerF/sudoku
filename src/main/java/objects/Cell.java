package objects;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class Cell {
    private TextField _field;
    private Integer _value;
    private int _cordX;  private int _cordY;
    private double _width; private double _height;
    public Cell() {
        _field = new TextField();

        _field.setAlignment(Pos.CENTER);
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

    public void createCellOnScreen(Pane parent, Integer dim) {
        if (parent == null) return;

        int squareOfDim = dim * dim;

        _field.setOnAction(actionEvent -> {
           if (Integer.parseInt(_field.getText()) == (_value)){
               _field.setDisable(true);
           }
           else {
               _field.setText(null);
           }
        });

        _width = parent.getWidth() / squareOfDim;
        _height = parent.getHeight() / squareOfDim;

        _field.setPrefWidth(_width);
        _field.setPrefHeight(_height);

        _field.setLayoutX(_cordX * _width);
        _field.setLayoutY(_cordY * _height);

        parent.getChildren().add(_field);
    }
}
