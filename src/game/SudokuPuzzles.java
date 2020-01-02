package game;

public enum SudokuPuzzles {

   PUZZLE1(getPuzzle1()), PUZZLE2(getPuzzle2());

    private int[][] puzzle;

    SudokuPuzzles(int[][] thePuzzle) {
        puzzle = thePuzzle;
    }

    public int[][] getPuzzle() {
        return puzzle;
    }

    private static int[][] getPuzzle0() {
        int[][] thePuzzle = new int[9][9];
        thePuzzle[0] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[1] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[2] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[3] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[4] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[5] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[6] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[7] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        thePuzzle[8] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
        return thePuzzle;
    }

    private static int[][] getPuzzle1() {
        int[][] thePuzzle = new int[9][9];
        thePuzzle[0] = new int[] {5, 3, 0, 0, 7, 0, 0, 0, 0};
        thePuzzle[1] = new int[] {6, 0, 0, 1, 9, 5, 0, 0, 0};
        thePuzzle[2] = new int[] {0, 9, 8, 0, 0, 0, 0, 6, 0};
        thePuzzle[3] = new int[] {8, 0, 0, 0, 6, 0, 0, 0, 3};
        thePuzzle[4] = new int[] {4, 0, 0, 8, 0, 3, 0, 0, 1};
        thePuzzle[5] = new int[] {7, 0, 0, 0, 2, 0, 0, 0, 6};
        thePuzzle[6] = new int[] {0, 6, 0, 0, 0, 0, 2, 8, 0};
        thePuzzle[7] = new int[] {0, 0, 0, 4, 1, 9, 0, 0, 5};
        thePuzzle[8] = new int[] {0, 0, 0, 0, 8, 0, 0, 7, 9};
        return thePuzzle;
    }

    private static int[][] getPuzzle2() {
        int[][] thePuzzle = new int[9][9];
        thePuzzle[0] = new int[] {0, 0, 0, 2, 6, 0, 7, 0, 1};
        thePuzzle[1] = new int[] {6, 8, 0, 0, 7, 0, 0, 9, 0};
        thePuzzle[2] = new int[] {1, 9, 0, 0, 0, 4, 5, 0, 0};
        thePuzzle[3] = new int[] {8, 2, 0, 1, 0, 0, 0, 4, 0};
        thePuzzle[4] = new int[] {0, 0, 4, 6, 0, 2, 9, 0, 0};
        thePuzzle[5] = new int[] {0, 5, 0, 0, 0, 3, 0, 2, 8};
        thePuzzle[6] = new int[] {0, 0, 9, 3, 0, 0, 0, 7, 4};
        thePuzzle[7] = new int[] {0, 4, 0, 0, 5, 0, 0, 3, 6};
        thePuzzle[8] = new int[] {7, 0, 3, 0, 1, 8, 0, 0, 0};
        return thePuzzle;
    }
}
