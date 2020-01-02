package game;

public class Solver {

    private SudokuGame game;

    public Solver(SudokuGame sudokuGame) {
        game = sudokuGame;
    }

    public void depthFirstSolve() {
        System.out.println("Solving");
    }
}
