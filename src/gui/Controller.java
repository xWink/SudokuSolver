package gui;

import game.SudokuGame;
import game.SudokuTile;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class Controller {

    private SudokuGUI gui;
    private SudokuGame game;
    private ArrayList<TileNode> nodes;

    public Controller(SudokuGUI theGui) {
        gui = theGui;
        game = new SudokuGame();
        setBoard();
    }

    /**
     * Get the whole sudoku board.
     * @return the TilePane containing all TileNode TextFields,
     * backgrounds, etc.
     */
    TilePane getBoard() {
        TilePane board = new TilePane();
        board.setPrefColumns(9);
        board.setPrefRows(9);
        for (TileNode node : nodes)
            board.getChildren().add(node.getText());
        return board;
    }

    /**
     * Creates the initial sudoku board.
     */
    void setBoard() {
        // Initialize list of all nodes of the board tiles
        nodes = new ArrayList<>();
        for (SudokuTile[] row : game.getBoard().getBoard()) {
            for (SudokuTile tile : row) {
                nodes.add(new TileNode(tile, getNewTextField(tile)));
                initBorder();
            }
        }

        // Make preset tiles uneditable
        for (TileNode node : nodes) {
            if (node.getTile().getValue() != 0) {
                node.getText().setEditable(false);
                node.getText().setMouseTransparent(true);
                node.getText().setFocusTraversable(false);
                node.getText().setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
            }
        }
    }

    private void initBorder() {
        if ((nodes.size() - 1) % 3 == 0 && (nodes.size() - 1) % 9 != 0) {
            setBorderWidths(nodes.size() - 1, new BorderWidths(0, 0, 0, 2));
            setBorderWidths(nodes.size() - 2, new BorderWidths(0, 2, 0, 0));
        } else if ((nodes.size() > 18 && nodes.size() < 28) || (nodes.size() > 45 && nodes.size() < 55))
            setBorderWidths(nodes.size() - 1, new BorderWidths(0, 0, 2, 0));
        else if ((nodes.size() > 27 && nodes.size() < 37) || (nodes.size() > 54 && nodes.size() < 64))
            setBorderWidths(nodes.size() - 1, new BorderWidths(2, 0, 0, 0));

        if (nodes.size() == 22 || nodes.size() == 25 || nodes.size() == 49 || nodes.size() == 52) {
            setBorderWidths(nodes.size() - 1, new BorderWidths(0, 0, 2, 2));
            setBorderWidths(nodes.size() - 2, new BorderWidths(0, 2, 2, 0));
        } else if (nodes.size() == 31 || nodes.size() == 34 || nodes.size() == 61 || nodes.size() == 58) {
            setBorderWidths(nodes.size() - 1, new BorderWidths(2, 0, 0, 2));
            setBorderWidths(nodes.size() - 2, new BorderWidths(2, 2, 0, 0));
        }
    }

    private void setBorderWidths(int nodeNum, BorderWidths widths) {
        nodes.get(nodeNum).getText().setBorder(new Border(new BorderStroke(
                gui.getRoot().getBackground().getFills().get(0).getFill(),
                BorderStrokeStyle.SOLID, null, widths)));
    }

    /**
     * Creates a new TextField for a given tile on the board.
     * @param tile the tile that will get a new textfield
     * @return the new textfield
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
     * Adds an event listener to a textfield.
     * The listener verifies changes to the textfield
     * and reverses them if they are not valid.
     * @param textField the textfield which will get a new event listener
     * @param tile the tile in the same node as the textfield
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
