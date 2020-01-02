package game;

public class SudokuTile {

    int value;
    SudokuTile[] row;
    SudokuTile[] col;
    SudokuTile[] area;

    public SudokuTile(SudokuTile[] theRow, SudokuTile[] theCol, SudokuTile[] theArea) {
        value = 0;
        row = theRow;
        col = theCol;
        area = theArea;
    }

    public int getValue() {
        return value;
    }

    public SudokuTile[] getRow() {
        return row;
    }

    public SudokuTile[] getCol() {
        return col;
    }

    public SudokuTile[] getArea() {
        return area;
    }

    public void setValue(int newValue) {
        value = newValue;
    }
}
