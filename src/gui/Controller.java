package gui;

import game.SudokuGame;
import game.SudokuTile;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;


public class Controller {

    private SudokuGUI gui;
    private SudokuGame game;
    private Solver solver;
    private TileNode[][] nodes;

    /**
     * Initializes a new game of sudoku, its board tiles, and a corresponding solver for the game.
     * @param theGui the gui for the sudoku game
     * @see SudokuGUI
     */
    public Controller(SudokuGUI theGui) {
        gui = theGui;
        game = new SudokuGame();
        solver = new Solver(this);
        initBoard();
    }

    /**
     * Starts a new game, clearing the board and replacing it with a
     * random new one.
     */
    void newGame() {
        game.newGame();
        initBoard();
        ((TilePane) gui.getRoot().getCenter()).getChildren().clear();
        gui.setBoard();
    }

    /**
     * Empties all editable cells on the sudoku board.
     */
    void reset() {
        game.reset();
        initBoard();
        ((TilePane) gui.getRoot().getCenter()).getChildren().clear();
        gui.setBoard();
    }

    /**
     * Returns the solver that contains the algorithms which can be used to solve the current sudoku puzzle.
     * @return the solver for the active puzzle
     * @see Solver
     */
    Solver getSolver() {
        return solver;
    }

    /**
     * Get the whole sudoku board as a 9x9 TilePane based on TileNodes.
     * @return the TilePane containing all TileNode TextFields,
     * backgrounds, etc.
     * @see TileNode
     */
    TilePane getBoard() {
        TilePane board = new TilePane();
        board.setPrefColumns(9);
        board.setPrefRows(9);
        for (TileNode[] row : nodes) {
            for (TileNode node : row) {
                board.getChildren().add(node.getText());
            }
        }
        return board;
    }

    /**
     * Creates the initial sudoku board for the GUI based on the board in the SudokuGame object.
     * @see SudokuGame
     * @see SudokuTile
     * @see TileNode
     */
    void initBoard() {
        // Initialize list of all nodes of the board tiles
        nodes = new TileNode[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                SudokuTile tile = game.getBoard().getBoard()[i][j];
                nodes[i][j] = new TileNode(tile, getNewTextField(tile));
                initBorder(i, j);
            }
        }

        // Make preset tiles uneditable
        for (TileNode[] row : nodes) {
            for (TileNode node : row) {
                if (!node.getTile().isEditable()) {
                    node.getText().setEditable(false);
                    node.getText().setMouseTransparent(true);
                    node.getText().setFocusTraversable(false);
                    node.getText().setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
                }
            }
        }
    }

    /**
     * Getter for the 2D array of TileNodes that contains the int values, editable state, and TextField for every
     * cell in the sudoku board.
     * @return the TileNodes that represent the entire sudoku board back-end and front-end
     * @see TileNode
     */
    TileNode[][] getNodes() {
        return nodes;
    }

    /**
     * Initializes borders for cells that are at the edges of a 3x3 box in the sudoku board.
     * @param row the row of the cell whose borders are initialized
     * @param col the column of the cell whose borders are initialized
     */
    private void initBorder(int row, int col) {
        if (row > 0 && col > 0 && row % 3 == 0 && col % 3 == 0) {
            setBorderWidths(nodes[row][col], new BorderWidths(2, 0, 0, 2));
            setBorderWidths(nodes[row][col - 1], new BorderWidths(2, 2, 0, 0));
            setBorderWidths(nodes[row - 1][col], new BorderWidths(0, 0, 2, 2));
            setBorderWidths(nodes[row - 1][col - 1], new BorderWidths(0, 2, 2, 0));
        } else if (col % 3 == 0 && col % 9 != 0) {
            setBorderWidths(nodes[row][col], new BorderWidths(0, 0, 0, 2));
            setBorderWidths(nodes[row][col - 1], new BorderWidths(0, 2, 0, 0));
        } else if (row > 0 && row % 3 == 0) {
            setBorderWidths(nodes[row - 1][col], new BorderWidths(0, 0, 2, 0));
            setBorderWidths(nodes[row][col], new BorderWidths(2, 0, 0, 0));
        }
    }

    /**
     * Sets the borders of a given TileNode to the solid colour of the sudoku board's background
     * and given BorderWidth properties.
     * @param node the TileNode whose TextField property will have its borders set
     * @param widths the BorderWidths applied to the TextField in the given TileNode
     * @see TileNode
     */
    private void setBorderWidths(TileNode node, BorderWidths widths) {
        node.getText().setBorder(new Border(new BorderStroke(
                gui.getRoot().getBackground().getFills().get(0).getFill(),
                BorderStrokeStyle.SOLID, null, widths)));
    }

    /**
     * Creates a new TextField for a given tile on the board.
     * @param tile the tile that will get a new textfield
     * @return the new textfield
     * @see SudokuTile
     */
    private TextField getNewTextField(SudokuTile tile) {
        TextField textField = new TextField(tile.getValue() == 0 ? "" : ((Integer) tile.getValue()).toString());
        textField.setPrefSize(40, 40);
        textField.setAlignment(Pos.CENTER);
        textField.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        textField.setOnMouseClicked(mouseEvent -> textField.selectAll());
        addTextEditListener(textField, tile);
        TilePane.setMargin(textField, new Insets(2));
        return textField;
    }

    /**
     * Adds an event listener to a textfield. The listener verifies changes to the textfield
     * and reverses them if they are not valid.
     * @param textField the textfield which will get a new event listener
     * @param tile the tile in the same node as the textfield
     * @see SudokuTile
     */
    private void addTextEditListener(TextField textField, SudokuTile tile) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(oldValue) || newValue.equals("")) return;
            try {
                int newInt = Integer.parseInt(newValue);
                if (newInt > 9 || newInt < 1)
                    throw new Exception();
                tile.setValue(newInt);
            } catch (Exception e) {
                Platform.runLater(() -> textField.setText(oldValue));
            }
        });
    }
}
