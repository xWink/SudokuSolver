package gui;

import game.SudokuGame;
import game.SudokuTile;


public class Solver {

    private Controller controller;
    private SudokuGame game;

    public Solver(Controller theController, SudokuGame sudokuGame) {
        controller = theController;
        game = sudokuGame;
    }

    public boolean depthFirstSolve() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                TileNode node = controller.getNodes()[row][col];
                if (node.getValue() == 0) {
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(num, row, col)) {
                            node.setValue(num);
                            if (depthFirstSolve()) {
                                return true;
                            } else {
                                node.setValue(0);
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(int num, int row, int col) {
        return isRowValid(num, row) && isColValid(num, col) && isAreaValid(num, row, col);
    }

    private boolean isRowValid(int num, int row) {
        TileNode[][] board = controller.getNodes();
        for (TileNode tile : board[row]) {
            if (tile.getValue() == num && tile.getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isColValid(int num, int col) {
        TileNode[][] board = controller.getNodes();
        for (int i = 0; i < 9; i++) {
            if (board[i][col].getValue() == num && board[i][col].getValue() != 0) {
                return false;
            }
        }
        return true;
    }

    private boolean isAreaValid(int num, int row, int col) {
        TileNode[][] board = controller.getNodes();
        int r = row - row % 3;
        int c = col - col % 3;

        for (int i = r; i < r + 3; i++) {
            for (int j = c; j < c + 3; j++) {
                if (board[i][j].getValue() == num && board[i][j].getValue() != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
