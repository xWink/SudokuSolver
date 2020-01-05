package game;

public class SudokuTile {

    int value;

    public SudokuTile() { }

    public void setValues() {
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int newValue) {
        value = newValue;
    }
}
