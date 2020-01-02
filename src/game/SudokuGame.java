package game;

public class SudokuGame {

    private SudokuBoard board;
    private SudokuPuzzles puzzle;

    public SudokuGame() {
        reset();
    }

    public void reset() {
        puzzle = SudokuPuzzles.values()[(int) (Math.random() * SudokuPuzzles.values().length)];
        board = new SudokuBoard();
        setNewValues();
    }

    private void setNewValues() {
        board.setAllValues(puzzle.getPuzzle());
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
