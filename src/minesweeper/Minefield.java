package minesweeper;

import java.util.*;

public class Minefield extends Observable {
    private final int NROWS = 16;
    private final int NCOLS = 30;
    private final int initialNumOfMines = 99;
    private final Game game;
    private final Square[][] squares;
    private int mineCount; 
    private int numOfEmptySquaresLeft;
    private final int[][] adjDirs = new int[][] {{-1, -1}, {-1, 0}, {-1, 1},
            {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public Minefield(Game game) {
        squares = new Square[NROWS][NCOLS];
        for (int i = 0; i < NROWS; i++) {
            for (int j = 0; j < NCOLS; j++) {
                squares[i][j] = new Square(i, j);
            }
        }
        this.game = game;
        this.mineCount = initialNumOfMines; 
        numOfEmptySquaresLeft = NROWS * NCOLS - initialNumOfMines;
    }
    public int getNRows() { return NROWS; }
    public int getNCols() { return NCOLS; }
	public int getMineCount() { return this.mineCount; }
    public void assignSquaresWithMine(int[] startPos) {
        Random rand = new Random();
        for (int numMinesAssigned = 0; numMinesAssigned < mineCount;) {
            int row = rand.nextInt(NROWS);
            int col = rand.nextInt(NCOLS);
            if (squares[row][col].hasMine()) continue;
            if ((row == startPos[0]) && (col == startPos[1])) continue;
            squares[row][col].setToHasMine();
            numMinesAssigned++;
        }
    }
    public void calcNumOfAdjSquaresWithMine() {
		// assign each square with a number that indicates how many adjacent squares 
		// has a mine
        for (int row = 0; row < NROWS; row++) {
            for (int col = 0; col < NCOLS; col++) {
                if (squares[row][col].hasMine()) continue;
                int adjSquaresWithMine = 0;
                for (int[] d : adjDirs) {
                    if ((row + d[0] < 0) || (row + d[0] >= NROWS) || 
                            (col + d[1] < 0) || (col + d[1] >= NCOLS)) continue;
                    if (squares[row + d[0]][col + d[1]].hasMine()) {
                        adjSquaresWithMine++;
                    }
                }
                squares[row][col].setNumOfAdjSquaresWithMine(adjSquaresWithMine);
            }
        }
    }
    public ArrayList<Square> revealSquare(int[] pos) {
        if (!game.hasStarted()) game.start(pos);
        Square squareClicked = squares[pos[0]][pos[1]];
        if (squareClicked.hasMine()) {
            // click on a mine and its game over - reveal all the squares with mine
            game.over();
            return findAllSquaresWithMine();
        }
        ArrayList<Square> squaresToReveal = new ArrayList<Square>();
        if (squareClicked.getNumOfAdjSquaresWithMine() == 0) {
            // the square clicked has no adjacent squares with mine
            // find all adjacent squares that always has no adjacent squares with mines 
            squaresToReveal = findAdjSquaresWithNoAdjMines(pos);
        } else { // squares has one or more adjacent mines.
            numOfEmptySquaresLeft--;
            squaresToReveal.add(squareClicked);
        }
        if (numOfEmptySquaresLeft <= 0) game.over();
        return squaresToReveal;
    } 
    private ArrayList<Square> findAllSquaresWithMine() { 
        ArrayList<Square> squaresWithMine = new ArrayList<Square>();
        for (int row = 0; row < NROWS; row++) {
            for (int col = 0; col < NCOLS; col++) {
                if (squares[row][col].hasMine()) {
                    squaresWithMine.add(squares[row][col]);
                }
            }
        }
        return squaresWithMine;
    } 
    private ArrayList<Square> findAdjSquaresWithNoAdjMines(int[] pos) {
        ArrayList<Square> adjSquaresWithNoAdjMines = new ArrayList<Square>();
        boolean[][] visited = new boolean[NROWS][NCOLS];
        findSquaresWithNoAdjMines(pos, adjSquaresWithNoAdjMines, visited);
        numOfEmptySquaresLeft -= adjSquaresWithNoAdjMines.size();
        return adjSquaresWithNoAdjMines;
    }
    private void findSquaresWithNoAdjMines(int[] pos, ArrayList<Square> adjSquaresWithNoAdjMines, boolean[][] visited) {
        visited[pos[0]][pos[1]] = true;
        Square currSquare = squares[pos[0]][pos[1]];
        adjSquaresWithNoAdjMines.add(currSquare);
        currSquare.reveal();
        if (currSquare.getNumOfAdjSquaresWithMine() > 0) return;
        int[] currSquarePos = currSquare.getPosition();
        int row = currSquarePos[0]; 
        int col = currSquarePos[1]; 
        for (int[] d: adjDirs) {
            int rowAdjSq = row + d[0]; 
            int colAdjSq = col + d[1]; 
            if ((rowAdjSq < 0) || (rowAdjSq >= NROWS) || (colAdjSq < 0) || (colAdjSq >= NCOLS)) continue;
            if (visited[rowAdjSq][colAdjSq]) continue; // this square has already been revealed 
            if (squares[rowAdjSq][colAdjSq].isFlagged()) continue; // this square has already been flagged 
            findSquaresWithNoAdjMines(new int[] {rowAdjSq, colAdjSq}, adjSquaresWithNoAdjMines, visited);
       }
    }
    public boolean canSquareBeRevealed(int[] pos) { return squares[pos[0]][pos[1]].canBeRevealed(); }
    public boolean isSquareFlagged(int[] pos) { return squares[pos[0]][pos[1]].isFlagged(); }
    public boolean isSquareRevealed(int[] pos) { return squares[pos[0]][pos[1]].isRevealed(); }
    public void toggleSquareFlagStatus(int[] pos) { 
		if (isSquareRevealed(pos)) return;
		squares[pos[0]][pos[1]].toggleFlaggedStatus(); 
		if (squares[pos[0]][pos[1]].isFlagged()) mineCount--;
		else mineCount++; // the square was unflagged
		updateObservers(); // update the mineCount value in the mineCount label
	}
	private void updateObservers() {
		setChanged();
		notifyObservers(mineCount);
	}
}
