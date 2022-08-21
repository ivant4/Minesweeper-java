package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

/*
 * Represent the game state and handles the change in game states
 */
public class Game {
    private enum GameState {
        NOT_RUNNING, 
        RUNNING, 
        OVER
    }
    private GameState gameState;
    private final AppFrame appFrame;
    private Minefield minefield;
    private final Timer timer;

    public Game() {
        timer = new Timer();
        minefield = new Minefield(this);
        appFrame = new AppFrame(this);
        this.gameState = GameState.NOT_RUNNING;
    }
    public void start(int[] startPos) { 
        minefield.assignSquaresWithMine(startPos);
        minefield.calcNumOfAdjSquaresWithMine();
        this.gameState = GameState.RUNNING;
        timer.start(); 
    }
    public boolean hasStarted() { return gameState != GameState.NOT_RUNNING; }
    public Minefield getMinefield() {return minefield; }
    public Timer getTimer() {return timer; }
    public void setGameOver() { // game is over
        this.gameState = GameState.OVER;
        timer.stop(); 
    }
    public void reset() {
        this.gameState = GameState.NOT_RUNNING;
        timer.reset();
        minefield = new Minefield(this);
        appFrame.reset(this);

    }
    public boolean isOver() { return gameState == GameState.OVER; }
}
