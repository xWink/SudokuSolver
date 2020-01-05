package game;

public class SudokuTile {

    int value;
    boolean editable;

    public SudokuTile() {
        editable = true;
    }

    public int getValue() {
        return value;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean isEditable) {
        editable = isEditable;
    }

    public void setValue(int newValue) {
        value = newValue;
    }
}
