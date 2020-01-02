package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SudokuGUI extends Application {

    private Stage primaryStage;
    private Controller controller;
    private BorderPane root;

    @Override
    public void start(Stage stage) throws Exception {
        primaryStage = stage;
        primaryStage.setTitle("Sudoku!");
        root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        controller = new Controller(this);
        setBoard();
        root.setBottom(controller.getBottom());
        BorderPane.setMargin(root.getBottom(), new Insets(3));
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    void setBoard() {
        root.setCenter(controller.getBoard());
        BorderPane.setMargin(root.getCenter(), new Insets(2, 0, 0, 2));
    }

    public BorderPane getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
