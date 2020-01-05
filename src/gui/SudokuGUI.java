package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class SudokuGUI extends Application {

    private Controller controller;
    private BorderPane root;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Sudoku!");
        root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        controller = new Controller(this);
        setBoard();
        root.setBottom(getBottom());
        BorderPane.setMargin(root.getBottom(), new Insets(3));
        Scene scene = new Scene(root, 400, 450);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Updates the sudoku board on the gui based on the TileNodes in the controller.
     */
    void setBoard() {
        root.setCenter(controller.getBoard());
        BorderPane.setMargin(root.getCenter(), new Insets(2, 0, 0, 2));
    }

    /**
     * Returns the root BorderPane.
     * @return the root node of the gui (BorderPane)
     */
    public BorderPane getRoot() {
        return root;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates an HBox which acts as the bottom of the BorderPane root in the gui. The HBox contains
     * buttons to create a new game, reset the current game, and to solve the sudoku board.
     * @return an HBox containing buttons for the sudoku gui
     */
    private HBox getBottom() {
        Button newGame = new Button("New Game");
        newGame.styleProperty().setValue("-fx-background-insets: 0 0 0 0, 0, 1, 2;");
        newGame.setOnAction(actionEvent -> controller.newGame());

        Button reset = new Button("Reset");
        reset.styleProperty().setValue("-fx-background-insets: 0 0 0 0, 0, 1, 2;");
        reset.setOnAction(actionEvent -> controller.reset());

        Button depthFirst = new Button("Depth First");
        depthFirst.styleProperty().setValue("-fx-background-insets: 0 0 0 0, 0, 1, 2;");
        depthFirst.setOnAction(actionEvent -> {
            controller.reset();
            controller.getSolver().depthFirstSolve();
            controller.initBoard();
            setBoard();
        });

        HBox spacer = new HBox();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox bottom = new HBox();
        bottom.getChildren().addAll(newGame, reset, spacer, depthFirst);
        return bottom;
    }
}
