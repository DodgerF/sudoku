package controllers;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import objects.Cell;
import objects.Grid;

import java.lang.reflect.Field;

public class GridDrawer {
    private Grid _grid;
    public GridDrawer(Grid grid) {
        _grid = grid;
    }

    public void drawOnParent(Pane parent) {
        for (Cell[] cells : _grid.massive) {
            for (Cell cell: cells){
                createCellOnScreen(cell, parent);
            }
        }
        for (int i = 1; i < _grid.getDim(); i++){
            parent.getChildren().add(new Line(parent.getWidth() / _grid.getDim() * i, 0,
                    parent.getWidth() / _grid.getDim() * i,  parent.getHeight()));
            parent.getChildren().add(new Line(0, parent.getHeight() / _grid.getDim() * i,
                    parent.getWidth(),  parent.getHeight() / _grid.getDim() * i));
        }
    }

    private void createCellOnScreen(Cell cell, Pane parent){
        if (parent == null) return;

        int squareOfDim = _grid.getDim() * _grid.getDim();
        TextField field = cell.getField();

        field.setOnAction(actionEvent -> {
            if (Integer.parseInt(field.getText()) == (cell.getValue())){
                field.setDisable(true);
            }
            else {
                field.setText(null);
            }
        });

        double width = parent.getWidth() / squareOfDim;
        double height = parent.getHeight() / squareOfDim;

        field.setPrefWidth(width);
        field.setPrefHeight(height);

        field.setLayoutX(cell.getCordX() * width);
        field.setLayoutY(cell.getCordY() * height);

        parent.getChildren().add(field);
    }
}
