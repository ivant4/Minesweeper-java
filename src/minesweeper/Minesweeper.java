package minesweeper;

/**
* starting point for the game
*/
public final class Minesweeper {
	public static void createNewGame() {
		Game game = new Game();
	}
	public static void main(String[] args) {
		Minesweeper minesweeper = new Minesweeper();
		minesweeper.createNewGame();
	}
}
