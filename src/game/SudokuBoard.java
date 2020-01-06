package game;


public class SudokuBoard {

    private SudokuTile[][] board;

    public SudokuBoard() {
        board = new SudokuTile[9][9];

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = new SudokuTile();
    }

    public void setValue(int row, int col, int value) {
        board[row][col].setValue(value);
    }

    public void setStartingValues(int[][] values) {
        if (values.length != board.length || values[0].length != board[0].length)
            return;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j].setValue(values[i][j]);
                if (values[i][j] != 0)
                    board[i][j].setEditable(false);
            }
        }
    }

    public SudokuTile[][] getBoard() {
        return board;
    }

    @Override
    public String toString() {
        String output = "-------------------\n";
        for (SudokuTile[] row : board) {
            output = output.concat("|");
            for (SudokuTile tile : row) {
                output = output.concat(tile.getValue() == 0 ? " " : ((Integer) tile.getValue()).toString()).concat("|");
            }
            output = output.concat("\n-------------------\n");
        }
        return output;
    }
}
