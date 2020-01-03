package game;


public class SudokuBoard {

    private SudokuTile[][] board;

    public SudokuBoard() {
        board = new SudokuTile[9][9];

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = new SudokuTile();

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j].setValues(board[i], getColumn(j), getArea(i, j));
    }

    private SudokuTile[] getColumn(int index) {
        SudokuTile[] column = new SudokuTile[board[index].length];
        for (int i = 0; i < column.length; i++){
            column[i] = board[i][index];
        }
        return column;
    }

    private SudokuTile[] getArea(int row, int col) {
        int areaR, areaC, num = 0;
        SudokuTile[] area = new SudokuTile[9];

        if (row < 3) areaR = 1;
        else if (row < 6) areaR = 4;
        else areaR = 7;

        if (col < 3) areaC = 1;
        else if (col < 6) areaC = 4;
        else areaC = 7;

        for (int i = areaR - 1; i <= areaR + 1; i++) {
            for (int j = areaC - 1; j <= areaC + 1; j++) {
                area[num++] = board[i][j];
            }
        }

        return area;
    }

    public void setValue(int row, int col, int value) {
        board[row][col].setValue(value);
    }

    public void setAllValues(int[][] values) {
        if (values.length != board.length || values[0].length != board[0].length)
            return;

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j].setValue(values[i][j]);
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
