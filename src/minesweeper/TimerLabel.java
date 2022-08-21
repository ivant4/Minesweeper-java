package minesweeper;

import javax.swing.JLabel;
import java.util.Observer;
import java.util.Observable;

public class TimerLabel extends JLabel implements Observer{
    private double elapsedTime;
    public TimerLabel(Game game) {
        super();
        elapsedTime = 0; 
        Timer timer = game.getTimer();
        timer.addObserver(this);
        this.setText(Integer.toString((int) elapsedTime));
    }
    public void update(Observable o, Object arg) {
        elapsedTime = (double) arg;
        this.setText(Integer.toString((int) elapsedTime));
    }
}
