package sample.sudoku;

import controllers.GridDrawer;
import controllers.ViewController;
import event.EventBus;
import event.events.GridGeneratedEvent;
import event.events.StartClickedEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SudokuApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SudokuApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 720);

        Sudoku<StartClickedEvent> sudokuController = new Sudoku<>();
        EventBus.getInstance().subscribe(StartClickedEvent.class, sudokuController);

        GridDrawer<GridGeneratedEvent> gridDrawer = new GridDrawer<>( ((ViewController)fxmlLoader.getController()).getPane());
        EventBus.getInstance().subscribe(GridGeneratedEvent.class, gridDrawer);

        stage.setTitle("Sudoku!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}