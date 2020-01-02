package game;

public class SudokuGame {

    private SudokuBoard board;

    public SudokuGame() {
        reset();
    }

    public void reset() {
        board = new SudokuBoard();
        setNewValues();
    }

    private void setNewValues() {
        int[][] values = new int[board.getBoard().length][board.getBoard()[0].length];
        values[0] = new int[]{5, 3, 0, 0, 7, 0, 0, 0, 0};
        values[1] = new int[]{6, 0, 0, 1, 9, 5, 0, 0, 0};
        values[2] = new int[]{0, 9, 8, 0, 0, 0, 0, 6, 0};
        values[3] = new int[]{8, 0, 0, 0, 6, 0, 0, 0, 3};
        values[4] = new int[]{4, 0, 0, 8, 0, 3, 0, 0, 1};
        values[5] = new int[]{7, 0, 0, 0, 2, 0, 0, 0, 6};
        values[6] = new int[]{0, 6, 0, 0, 0, 0, 2, 8, 0};
        values[7] = new int[]{0, 0, 0, 4, 1, 9, 0, 0, 5};
        values[8] = new int[]{0, 0, 0, 0, 8, 0, 0, 7, 9};
        board.setAllValues(values);
    }

    public void setValue(int row, int column, int value) {
        board.setValue(row, column, value);
    }

    public void printBoard() {
        System.out.println(board);
    }

    public SudokuBoard getBoard() {
        return board;
    }
}
