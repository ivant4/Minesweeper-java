package minesweeper;

import javax.swing.JLabel;
import java.util.Observer;
import java.util.Observable;

public class MineCountLabel extends JLabel implements Observer {
    public MineCountLabel(Game game) {
        super();
		Minefield minefield = game.getMinefield();
        minefield.addObserver(this);
        this.setText(Integer.toString(minefield.getMineCount()));
    }
    public void update(Observable o, Object arg) {
        this.setText(Integer.toString((Integer) arg));
    }
}
