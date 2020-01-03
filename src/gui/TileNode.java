package gui;

import game.SudokuTile;
import javafx.scene.control.TextField;

public class TileNode {

    private SudokuTile tile;
    private TextField text;

    public TileNode(SudokuTile sudokuTile, TextField textField) {
        tile = sudokuTile;
        text = textField;
    }

    public SudokuTile getTile() {
        return tile;
    }

    public TextField getText() {
        return text;
    }

    public void setValue(int value) {
        tile.setValue(value);
        text.setText(((Integer) value).toString());
    }
}
