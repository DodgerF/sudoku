module sample.sudoku {
    requires javafx.controls;
    requires javafx.fxml;


    opens sample.sudoku to javafx.fxml;
    exports sample.sudoku;
    exports controllers;
    opens controllers to javafx.fxml;
}