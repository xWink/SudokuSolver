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
        SudokuTile[][] board = game.getBoard().getBoard();
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board[row][col].getValue() == 0) {
                    for (int k = 1; k <= 9; k++) {
                        controller.getNodes().get(row * 9 + col).setValue(k);
                        if (isValid(board[row][col]) && depthFirstSolve())
                            return true;
                        controller.getNodes().get(row * 9 + col).setValue(controller.getNodes().get(row * 9 + col).getTile().getValue() + 1);
                    }
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValid(SudokuTile tile) {
        return isRowValid(tile) && isColValid(tile) && isAreaValid(tile);
    }

    private boolean isRowValid(SudokuTile tile) {
        for (SudokuTile otherTile : tile.getRow()) {
            if (tile.getValue() == otherTile.getValue()
                    && tile.getValue() != 0
                    && !tile.equals(otherTile)) {
                return false;
            }
        }
        return true;
    }

    private boolean isColValid(SudokuTile tile) {
        for (SudokuTile otherTile : tile.getCol()) {
            if (tile.getValue() == otherTile.getValue()
                    && tile.getValue() != 0
                    && !tile.equals(otherTile)) {
                return false;
            }
        }
        return true;
    }

    private boolean isAreaValid(SudokuTile tile) {
        for (SudokuTile otherTile : tile.getArea()) {
            if (tile.getValue() == otherTile.getValue()
                    && tile.getValue() != 0
                    && !tile.equals(otherTile)) {
                return false;
            }
        }
        return true;
    }
}
