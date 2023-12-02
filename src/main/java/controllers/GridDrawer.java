package controllers;

import event.IEvent;
import event.IEventListener;
import event.events.GridGeneratedEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import objects.Cell;
import objects.Grid;


public class GridDrawer<T extends IEvent> implements IEventListener<T> {
    private final Pane _parent;
    public GridDrawer(Pane pane) {
        _parent = pane;
    }

    @Override
    public void handle(T event) {
        _parent.getChildren().clear();
        drawOnParent(((GridGeneratedEvent)event).GRID);
    }
    public void drawOnParent(Grid grid) {
        for (Cell[] cells : grid.massive) {
            for (Cell cell: cells){
                createCellOnScreen(cell, grid.getDim());
            }
        }
        for (int i = 1; i < grid.getDim(); i++){
            _parent.getChildren().add(new Line(_parent.getWidth() / grid.getDim() * i, 0,
                    _parent.getWidth() / grid.getDim() * i,  _parent.getHeight()));
            _parent.getChildren().add(new Line(0, _parent.getHeight() / grid.getDim() * i,
                    _parent.getWidth(),  _parent.getHeight() / grid.getDim() * i));
        }
    }

    private void createCellOnScreen(Cell cell, int dim){

        int squareOfDim = dim * dim;
        TextField field = cell.getField();

        field.setOnAction(actionEvent -> {
            if (Integer.parseInt(field.getText()) == (cell.getValue())){
                field.setDisable(true);
            }
            else {
                field.setText(null);
            }
        });

        double width = _parent.getWidth() / squareOfDim;
        double height = _parent.getHeight() / squareOfDim;

        field.setPrefWidth(width);
        field.setPrefHeight(height);

        field.setLayoutX(cell.getCordX() * width);
        field.setLayoutY(cell.getCordY() * height);

        _parent.getChildren().add(field);
    }
}
