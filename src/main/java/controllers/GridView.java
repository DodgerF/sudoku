package controllers;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import objects.Cell;
import objects.Grid;

public class GridView {
    private Grid _grid;
    public GridView(Grid grid) {
        _grid = grid;
    }

    public void drawOnParent(Pane parent) {
        for (Cell[] cells : _grid.massive) {
            for (Cell cell: cells){
                cell.createCellOnScreen(parent, _grid.getDim());
            }
        }
        for (int i = 1; i < _grid.getDim(); i++){
            parent.getChildren().add(new Line(parent.getWidth() / _grid.getDim() * i, 0,
                    parent.getWidth() / _grid.getDim() * i,  parent.getHeight()));
            parent.getChildren().add(new Line(0, parent.getHeight() / _grid.getDim() * i,
                    parent.getWidth(),  parent.getHeight() / _grid.getDim() * i));
        }
    }
}
