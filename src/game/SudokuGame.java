package game;

public class SudokuGame {

    private SudokuBoard board;
    private SudokuPuzzles puzzle;

    public SudokuGame() {
        newGame();
    }

    public void newGame() {
        puzzle = SudokuPuzzles.values()[(int) (Math.random() * SudokuPuzzles.values().length)];
        board = new SudokuBoard();
        setStartingValues();
    }

    public void reset() {
        board = new SudokuBoard();
        setStartingValues();
    }

    private void setStartingValues() {
        board.setStartingValues(puzzle.getPuzzle());
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
