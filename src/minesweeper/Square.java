package minesweeper;

public class Square {
    private enum SquareState {
        REVEALED,
        COVERED,
        FLAGGED,
    }
    private boolean hasMine = false;
    private SquareState squareState;
    private int numOfAdjSquaresWithMine;
    private final int row, col; 

    public Square(int row, int col) {
        this.row = row;
        this.col = col;
        this.squareState = SquareState.COVERED;
    }
    public int[] getPosition() { return new int[] {row, col}; } 
    public void setToHasMine() { hasMine = true; }
    public boolean hasMine() { return hasMine; }
    public void setNumOfAdjSquaresWithMine(int numOfAdjSquaresWithMine) {
        this.numOfAdjSquaresWithMine = numOfAdjSquaresWithMine;
    }
    public int getNumOfAdjSquaresWithMine() { return numOfAdjSquaresWithMine; }
    public boolean canBeRevealed() { return squareState == SquareState.COVERED; }
    public void reveal() { squareState = SquareState.REVEALED; }
    public void toggleFlaggedStatus() { 
        if (squareState == SquareState.COVERED) squareState = SquareState.FLAGGED; // flag the square
        else squareState = SquareState.COVERED; // unflag the square
    }
    public boolean isFlagged() { return squareState == SquareState.FLAGGED; }
    public boolean isRevealed() { return squareState == SquareState.REVEALED; }
}
