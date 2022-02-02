package minesweeper;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class Game {
    private enum GameState {
        NOT_RUNNING, 
        RUNNING, 
        OVER
    }
    private Timer timer;
    private Minefield minefield;
    private GameFrame gameFrame;
    private GameState gameState;

    public Game() {
        timer = new Timer();
        minefield = new Minefield(this);
        gameFrame = new GameFrame(this);
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
    public void over() { // game is over
        this.gameState = GameState.OVER;
        timer.stop(); 
    }
    public void reset() {
        this.gameState = GameState.NOT_RUNNING;
        timer.reset();
        minefield = new Minefield(this);
        gameFrame.reset(this);

    }
    public boolean isOver() { return gameState == GameState.OVER; }
    public static void main(String[] args) {
        Game game = new Game();
    }
}
